package day03.classDemo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class IpLocal {
	/**
	 * 此类用于读取IP地址归属地。
	 * 获得一个包含ip归属地的基本信息的List
	 */
	public static List<IpBean> getIpLocal() {
		List<IpBean> list = new ArrayList<>();
		try (BufferedReader bw = new BufferedReader(new FileReader("F:\\JAVA视频\\韩利鹏\\day02-app升级\\day03-ip地址归属地\\ip.txt"));)
		{
			String line;
			//1.0.1.0|1.0.3.255|16777472|16778239|亚洲|中国|福建|福州||电信|350100|China|CN|119.306239|26.075302
			while((line=bw.readLine())!=null) {
				String[] split = line.split("\\|", 8);
				String ipStart = split[0];
				String ipEnd = split[1];
				Long ipDecStart = Long.parseLong(split[2]);
				Long ipDecEnd = Long.parseLong(split[3]);
				String province = split[6];
				String city = split[7];
				IpBean ipBean = new IpBean(ipStart,ipEnd,ipDecStart,ipDecEnd,province,city);
				list.add(ipBean);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	

}
