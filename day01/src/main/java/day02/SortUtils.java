package day02;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class SortUtils {
	
	public static void sortByVersion(List<String> list) {
		Collections.sort(list, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				
				return o1.compareTo(o2);
			}

		});
	}
	public static void sortByTime(List<AppBean> list) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Collections.sort(list, new Comparator<AppBean>() {

			@Override
			public int compare(AppBean o1, AppBean o2) {
				//字符串转时间
				Date o1date = null;
				Date o2date =null ;
				try {
					o1date = sdf.parse(o1.getDate());
					o2date = sdf.parse(o2.getDate());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return o1date.before(o2date)? -1:1;//o1date在o2date之前则返回true
			}
		});
	}

}
