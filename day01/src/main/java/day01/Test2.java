package day01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * 获得两人的共同好友
 * @author Administrator
 *
 */
public class Test2 {

	public static void main(String[] args) {
		Map<String, List<String>> map = getMap();//获得Map  不可增删
		
		Set<String> keySet = map.keySet();//获得key 根据key 得到list 取交集 set不能通过索引值来获取,转化为List
		ArrayList<String> arrayList = new ArrayList<>(keySet);
		
		try(
				BufferedWriter bw = new BufferedWriter(new FileWriter("F:\\JAVA视频\\赵念行\\好友join.txt"));
				) {
			
			for (int i=0;i<keySet.size()-1;i++) {
				String si = arrayList.get(i);//获得了key值
				List<String> listi = map.get(si);
				ArrayList<String> arrayList2 = new ArrayList<String>(listi);
				
				for (int j=i+1;j<keySet.size();j++) {
					String sj = arrayList.get(j);//获得了key值
					List<String> listj = map.get(sj);
					arrayList2.retainAll(listj);
					if(arrayList2!=null&&arrayList2.size()>0) {
						System.out.println(si+"和"+sj+"的共同好友为："+arrayList2);
						bw.write(si+"和"+sj+"的共同好友为："+arrayList2);
						bw.newLine();
					}
				}
			}
			bw.flush();
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}

	private static Map<String,List<String>> getMap() {
		Map<String,List<String>> map = new HashMap<>();
		try (
				BufferedReader br = new BufferedReader(new FileReader("F:\\JAVA视频\\赵念行\\好友.txt"));
				){
			String readLine = null;
			while ((readLine=br.readLine())!=null) {
				//System.out.println(readLine);
				String[] split = readLine.split(":");
				String key = split[0];
				String[] split2 = split[1].split(",");//数组直接放进可以？数组长度不定
				List<String> asList = Arrays.asList(split2);//转化为List，List不能增改
				map.put(key, asList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}
