package day06.selfcode;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class SortDateUtils {
	public static void sortByDate(List<Date> list) {
		Collections.sort(list, new Comparator<Date>() {

			@Override
			public int compare(Date o1, Date o2) {
				
				return o1.before(o2)?-1:1;
			}
		});
	}

}
