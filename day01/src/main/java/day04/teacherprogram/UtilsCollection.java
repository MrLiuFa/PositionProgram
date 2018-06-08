package day04.teacherprogram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;

public class UtilsCollection {
	
	public static float getAve(List<Integer> value) {
		int sum = 0;
		float ave;
		for (Integer i : value) {
			sum += i;
		}
		return ave = sum*1.0f/value.size();
	}

	public static void sortByAveRate(ArrayList<Entry<Integer, String>> aveList) {
		Collections.sort(aveList, new Comparator<Entry<Integer, String>>() {

			@Override
			public int compare(Entry<Integer, String> o1, Entry<Integer, String> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		
	}
	public static void sortByHotMovie(List<Entry<String, Integer>> hotList) {
		Collections.sort(hotList, new Comparator<Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue()-o1.getValue();
			}
		});
		
	}
	public static void sortByMovieRate(List<Entry<String, String>> list) {
		Collections.sort(list, new Comparator<Entry<String, String>>() {

			@Override
			public int compare(Entry<String, String> o1, Entry<String, String> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		
	}

	
}
