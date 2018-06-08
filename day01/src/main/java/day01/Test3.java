package day01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Test3 {

	public static void main(String[] args) {
		Map<String,String[]> map = getMap();//获得Map
		Set<Entry<String,String[]>> entrySet = map.entrySet();
		
		//获得好友个数
		HashMap<String, Integer> count = new HashMap<String,Integer>();
		for (Entry<String, String[]> entry : entrySet) {
			count.put(entry.getKey(), entry.getValue().length);
		}
		
		//获得相互之间的好友
		//仍然需要 获得创建Map以保证 用户与其好友的对应关系
		Map<String,List<String>> map2 = new HashMap<>();
		for (Entry<String, String[]> entry : entrySet) {
			List<String> list2 = Arrays.asList(entry.getValue());//转化为集合 方便取交集
			List<String> list = new ArrayList<>(list2);//化为可编辑的对象
			map2.put(entry.getKey(), list);//得到了可编辑Map对象。
		}
		
		//对上述Map对象编辑 Map不能通过索引值遍历，需转化为List
		Set<String> keySet = map2.keySet();
		List<String > list3 = new ArrayList<>(keySet);
		//System.out.println(list3);
		for (int i=0;i<map2.size()-1;i++) {
			List<String> list = map2.get(list3.get(i));
			//List<String> listi = new ArrayList(list);
			for(int j=i+1;j<map2.size();j++) {
				List<String> list2 = map2.get(list3.get(j));
				//listi.retainAll(list2);
				list.retainAll(list2);
				if(list!=null&&list.size()>0)
				System.out.println(list3.get(i)+"和"+list3.get(j)+"的共同好友为："+list);
			}
		}
		
		
	}
	private static Map<String,String[]> getMap() {
		Map<String,String[]> map = new HashMap<>();
		try (
				BufferedReader br = new BufferedReader(new FileReader("F:\\JAVA视频\\赵念行\\好友.txt"));
				){
			String readLine = null;
			while ((readLine=br.readLine())!=null) {
				String[] split = readLine.split(":");
				String key = split[0];
				String[] split2 = split[1].split(",");//数组直接放进可以？数组长度不定
				map.put(key, split2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}
