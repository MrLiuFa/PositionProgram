package day04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.mchange.v2.util.ComparatorUtils;

public class SortUtils {
	/**
	 * 对List进行排序，降序排列。
	 * @param list
	 */
	public static void sortList(List<MovieJson> list) {
		//降序
		Collections.sort(list, new Comparator<MovieJson>() {

			@Override
			public int compare(MovieJson o1, MovieJson o2) {
				
				return o2.getRate()-o1.getRate();
			}
		});
	}
	/**
	 * 求得每个用户的评分的平均值
	 * @param list
	 * @return
	 */
	public static double average(List<MovieJson> list) {
		int sum = 0;
		int size = list.size();
		for (MovieJson movieJson : list) {
			int rate = movieJson.getRate();
			sum += rate;
		}
		double ave = 1.0*sum/size;
		return ave;
	}
	public static List<Entry<Integer,Double>> sortMap(Set<Entry<Integer,Double>> set){
		List<Entry<Integer,Double>> list = new ArrayList<>(set);
		Collections.sort(list, new Comparator<Entry<Integer,Double>>() {

			@Override
			public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
				double b = o2.getValue()-o1.getValue();
				return b>0 ? 1:b==0 ? 0:-1;
			}
		});
		return list;
	}
	
	public static void sortHotMovie(List<Entry<String,Integer>> list){
		Collections.sort(list, new Comparator<Entry<String,Integer>>() {

			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				
				return o2.getValue()-o1.getValue();//降序
			}
		});
		
	}
	
	public static void sortRate(List<Entry<String,Double>> list){
		Collections.sort(list, new Comparator<Entry<String,Double>>() {

			@Override
			public int compare(Entry<String, Double> o1, Entry<String, Double> o2) {
				
				return o2.getValue()-o1.getValue()>0 ? 1:-1;//降序
			}
		});
		
	}

}
