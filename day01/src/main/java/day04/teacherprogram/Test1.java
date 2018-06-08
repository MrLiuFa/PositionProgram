package day04.teacherprogram;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * 每个用户评分最高的三部电影
 * @author Administrator
 *
 */
public class Test1 {

	public static void main(String[] args) {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		QueryRunner runner = new QueryRunner(dataSource);
		
		String mktable = "create table t_1(uid Int ,max1 Int ,max2 Int ,max3 Int )";
		String mktable2 = "create table t_2(uid Int ,max1 Int ,max2 Int ,max3 Int,Average varchar(6))";
		String mktable3 = "create table t_3(uid Int ,Average varchar(6))";
		try {
			//runner.update(mktable);
			//runner.update(mktable2);
			runner.update(mktable3);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		Map<Integer,List<Integer>> map = new HashMap<>();
		List<MovieBean> movieBean = MovieShared.getMovieBean();
		//获得每个用户的评价信息
		for (MovieBean b:movieBean) {
			
			List<Integer> list = map.getOrDefault(b.getUid(), new ArrayList<Integer>());
			list.add(b.getRate());
			map.put(b.getUid(), list);
			
		}
		
		//对Map进行局部排序 List排序
		Set<Entry<Integer,List<Integer>>> entrySet = map.entrySet();
		List<Integer> value = null ;
		Map<Integer,String> aveMap = new HashMap<>();
		for (Entry<Integer, List<Integer>> entry : entrySet) {
			
			value = entry.getValue();
			Collections.sort(value);//升序的
			int uid = entry.getKey();
			int max1 = value.get(value.size()-1);
			int max2 = value.get(value.size()-2);
			int max3 = value.get(value.size()-3);
			float ave = UtilsCollection.getAve(value);//获得平均值
			String format = String.format("%.2f", ave);
			//String sql = "insert into t_2 values(?,?,?,?,?);";
			//.最大方(评分平均值高)的3个用户的uid和评分平均值
			aveMap.put(uid, format);
			
		/*	try {
				runner.update(sql,uid,max1,max2,max3,format);
			} catch (SQLException e) {
				e.printStackTrace();
			}*/
			/*for(int i=value.size()-1;i>Math.min(-1, value.size()-4);i--) {//如果有小于3的，则不会出问题
				System.out.println(entry.getKey()+"-->"+value.get(i));
			}
			System.out.println("*****************");*/
			
			//每个用户的uid和评分的平均值
			
			//System.out.println(entry.getKey()+"---->"+ave);
		}
		Set<Entry<Integer,String>> entrySet2 = aveMap.entrySet();
		ArrayList<Entry<Integer,String>> aveList = new ArrayList<>(entrySet2);
		UtilsCollection.sortByAveRate(aveList);
		String sql2 = "insert into t_3 values(?,?);";
		for (Entry<Integer, String> entry : aveList) {
			try {
				runner.update(sql2,entry.getKey(),entry.getValue());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(entry.getKey()+"   "+entry.getValue());
		}
		
		
		
	
		
	}

	
}
