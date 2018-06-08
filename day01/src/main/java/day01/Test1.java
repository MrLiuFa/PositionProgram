package day01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
/**
 * 获得好友数量并按降序排列
 * @author Administrator
 *
 */
public class Test1 {

	public static void main(String[] args) {
		Map<String,Integer> countMap = getCountMap();
		Set<Entry<String, Integer>> entrySet = countMap.entrySet();
		//转化为list  
		List<Entry<String, Integer>> list = new ArrayList<>(entrySet);
		//完成排序过程
		Collections.sort(list, new Comparator<Entry<String, Integer>>() {
 
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				// TODO Auto-generated method stub
				return o2.getValue()-o1.getValue();
			}
		});
		//保存到文件中
		try (
			BufferedWriter bw = new BufferedWriter(new FileWriter("F:\\\\JAVA视频\\\\赵念行\\\\好友account.txt"));
			){
			for (Entry<String, Integer> entry : list) {
				System.out.println(entry);
				bw.write(entry.toString());
				bw.newLine();
			}
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		//输出结果 保存在数据库中
	}

	private static Map<String,Integer> getCountMap() {
		Map<String,Integer> map = new HashMap<>();
		try (
				BufferedReader br = new BufferedReader(new FileReader("F:\\JAVA视频\\赵念行\\好友.txt"));
				){
			String readLine = null;
			while ((readLine=br.readLine())!=null) {
				//System.out.println(readLine);
				String[] split = readLine.split(":");
				String key = split[0];
				int length = split[1].split(",").length;
				map.put(key, length);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}
