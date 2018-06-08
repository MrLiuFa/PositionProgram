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

public class Test5 {

	public static void main(String[] args) {
		
		List<MovieBean> list = MovieShared.getMovieBean();
		Map<String,List<Integer>> aveMoviemap = new HashMap<>();
		for (MovieBean movieBean : list) {
			
			String key = movieBean.getMovie();
			List<Integer> list2 = aveMoviemap.getOrDefault(key, new ArrayList<Integer>());
			int rate = movieBean.getRate();
			list2.add(rate);
			aveMoviemap.put(key, list2);
		
		}
		
		//求每部电影的平均值
		Map<String,String> moviemap = new HashMap<>();
		Set<Entry<String,List<Integer>>> entrySet = aveMoviemap.entrySet();
		for (Entry<String, List<Integer>> entry : entrySet) {
			List<Integer> value = entry.getValue();
			float ave = UtilsCollection.getAve(value);
			String format = String.format("%.2f", ave);
			moviemap.put(entry.getKey(), format);
			
		}
		//评价最高的3部电影id和评分均值
		Set<Entry<String,String>> set = moviemap.entrySet();
		//排序
		ArrayList<Entry<String,String>> arrayList = new ArrayList<>(set);
		UtilsCollection.sortByMovieRate(arrayList);
		
		//打印
		for(int i=0;i<100;i++) {
			Entry<String, String> entry = arrayList.get(i);
			System.out.println(entry.getKey()+"\t"+entry.getValue());
		}
		//保存到数据库
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		QueryRunner runner = new QueryRunner(dataSource);
		String mktable = "create table t_hotMovie(movie varchar(10),rate varchar(10));";
		try {
			runner.update(mktable);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String sql = "insert into t_hotMovie values(?,?)";
		for (Entry<String, String> entry : arrayList) {
			String movie = entry.getKey();
			String rate = entry.getValue();
			try {
				runner.update(sql, movie, rate);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
