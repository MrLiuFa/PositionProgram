package day05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
/**
 * 根据给的用户上网日志记录数据，计算出总流量最高的手机号Top3
 *
 */
public class Test4 {
public static void main(String[] args) {
		
		Map<String,Long> map = new HashMap<>();
		//根据给的用户上网日志记录数据，计算出总流量最高的网站Top3(网站例如：v.baidu.com，weibo.com)
		try (
				BufferedReader br = new BufferedReader(new FileReader("F:\\JAVA视频\\韩利鹏\\day04-电影排行\\day05-流量统计\\http.log"));
				FileWriter fw = new FileWriter("F:\\JAVA视频\\韩利鹏\\day04-电影排行\\day05-流量统计\\httpwork4.txt");
				){
			String line = null;
			while((line=br.readLine())!=null) {
				String phoneNumber = line.split("\t")[0];
				String[] split = line.split("\t")[1].split(" ");
				Long up = Long.parseLong(split[1]);
				Long down = Long.parseLong(split[2]);
				Long sum = map.getOrDefault(phoneNumber, 0L);
				sum = sum + up +down;
				map.put(phoneNumber, sum);//相应数据放入Map中
			}
			//对Map进行排序
			Set<Entry<String,Long>> set = map.entrySet();
			ArrayList<Entry<String,Long>> list = new ArrayList<>(set);
			Utils.sortByFlux(list);
			//保存到文件中
			String s = "手机号"+"\t"+"流量"+"\n";
			fw.write(s);
			
			//for (int i=0;i<Math.min(3, list.size());i++) {
			for (int i=0;i<list.size();i++) {
				Entry<String, Long> entry = list.get(i);
				String website = entry.getKey();
				Long flux = entry.getValue();
				String str = website+"\t"+flux+"\n";
				fw.write(str);
				
				System.out.println(entry.getKey()+"\t\t"+entry.getValue());
			}
			fw.flush();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
