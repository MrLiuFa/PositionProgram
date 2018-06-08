package day04.teacherprogram;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class Test4_5 {

	public static void main(String[] args) {
		// .最热门的3部电影id和评价次数
		//5.评价最高的3部电影id和评分均值
		List<MovieBean> list = MovieShared.getMovieBean();
		Map<String,Integer> map = new HashMap<>(); 
		
		//得到统计结果。
		for (MovieBean movieBean : list) {
			String key = movieBean.getMovie();
			
			Integer num = map.getOrDefault(key, 0);
			num++;
			map.put(key, num);
			
			int rate = movieBean.getRate();
		
			
			
		}
		//对Map进行排序
		Set<Entry<String,Integer>> entrySet = map.entrySet();
		List<Entry<String,Integer>> hotList = new ArrayList<>(entrySet);
		UtilsCollection.sortByHotMovie(hotList);
		for (Entry<String, Integer> entry : hotList) {
			System.out.println(entry.getKey()+"\t"+entry.getValue());
		}
		ComboPooledDataSource source = new ComboPooledDataSource();
		QueryRunner runner = new QueryRunner(source);
		
		String mktable = "create table t_ratecount(id int auto_increment primary key,movieid varchar(10),ratecount int);";
		try {
			runner.update(mktable);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String sql = "insert into t_ratecount values(?,?,?)";
		for (Entry<String, Integer> entry : hotList) {
			String key = entry.getKey();
			Integer value = entry.getValue();
			try {
				runner.update(sql,null,key,value);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		


	}

}
