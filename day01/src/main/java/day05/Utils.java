package day05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map.Entry;

public class Utils {

	public static void sortByFlux(ArrayList<Entry<String, Long>> list) {
		Collections.sort(list, new Comparator<Entry<String, Long>>() {

			@Override
			public int compare(Entry<String, Long> o1, Entry<String, Long> o2) {
				return o2.getValue()-o1.getValue()>0?1:-1;
			}
		});
		
	}

}
