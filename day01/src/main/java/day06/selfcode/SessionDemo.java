package day06.selfcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class SessionDemo {
	public static void main(String[] args) {
		Map<String,List<Date>> map = new HashMap<>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss",Locale.US);
		try (BufferedReader br = new BufferedReader(new FileReader("F:\\JAVA视频\\韩利鹏\\day05-流量统计\\day06-session分析\\access.log.fensi"));){
			String line = null;
			while((line=br.readLine())!=null) {
				String[] split = line.split(" ");
				String ip = split[0];
				//System.out.println(ip);
				String time = split[3].substring(1, split[3].length());
				//System.out.println(time);
				Date date = sdf.parse(time);
				//往map中添加元素
				List<Date> list = map.getOrDefault(ip, new ArrayList<Date>());
				list.add(date);
				map.put(ip, list);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Set<Entry<String,List<Date>>> entrySet = map.entrySet();
		for (Entry<String, List<Date>> entry : entrySet) {
			String ip = entry.getKey();
			List<Date> listDate = entry.getValue();
			//对listDate进行排序
			SortDateUtils.sortByDate(listDate);
			for (Date date : listDate) {
				String format = dateFormat.format(date);
				System.out.println(ip+"\t"+format);
			}
			
		}
	}

}
