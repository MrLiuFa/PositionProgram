package day03.classDemo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class AccessDemo {

	public static void main(String[] args) {
		
		//创建一个Map数组,用于对数据存放     省份和访问次数
		Map<String,Integer> map = new HashMap<>();
		try (BufferedReader br = new BufferedReader(new FileReader("F:\\JAVA视频\\韩利鹏\\day02-app升级2\\day02-app升级\\day03-ip地址归属地\\access.log"));)
		{
			String line = null;
			while ((line=br.readLine())!=null) {
				
				String[] split = line.split("\\|",3);
				String ip = split[1];
				Long ipLong = Utils.transUserIpToDecIp(ip);//将ip地址转化为十进制的长整型整数
				
				IpBean bean = Utils.SearchIpByBinary(ipLong);//查找IP地址对应的对象
				String province = bean.getProvince();//获得所属省份
				
				Integer count = map.getOrDefault(province, 0);//对map进行默认处理。返回值是value；
				count++;
				map.put(province, count);
			}
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		Set<Entry<String,Integer>> entrySet = map.entrySet();
		ArrayList<Entry<String,Integer>> arrayList = new ArrayList<>(entrySet);
		Utils.sort(arrayList);
		int sum = 0;
		for (Entry<String, Integer> entry : arrayList) {
			System.out.println(entry.getKey()+"--->"+entry.getValue());
			sum += entry.getValue();
		}
		System.out.println(sum);
		
		
	}
	

}
