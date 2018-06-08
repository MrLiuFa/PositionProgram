package day03.selfScrip;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * 	通过计算access.log中的用户行为数据，
	统计出各个省份访问量（一次请求记作一次独立的访问量），
	并按照各个省份的访问量的从高到低进行排序

 * @author Administrator
 *
 */
public class FlowAccout {

	public static void main(String[] args) {
		int count = 0;
		Map<String,Integer> map = new HashMap<>();
		List<LogBean> logList = logList() ;//用户登陆的List ArrayList
		List<LocationBean> ipList = ipList();
		
		for(LogBean logBean:logList) {
			
			Long ip = logBean.getIp();
			String string = ip.toString();
			for (LocationBean loc:ipList) {
				String provience = loc.getProvience();
				//Integer ipDecimalS = Integer.valueOf(loc.getIpDecimalS());
				//Integer ipDecimalE = Integer.valueOf(loc.getIpDecimalE());
				String ipDecimalS = loc.getIpDecimalS();
				String ipDecimalE = loc.getIpDecimalE();
				if((string.compareTo(ipDecimalS)>=0?true:false)&&(string.compareTo(ipDecimalE)<=0?true:false)) {
					if (map.containsKey(provience)) {
						int c = map.get(provience);
						c += 1;
						map.put(provience, c);
						break;
					}else {
						map.put(provience, 1);
						break;
					}
				}
			}
		}
		
		Set<String> keySet = map.keySet();
		for (String string : keySet) {
			System.out.println(string+": "+map.get(string));
			count += map.get(string);
		}
		System.out.println(count);
	}

	private static List<LocationBean> ipList() {
		
		List<LocationBean> locat = new ArrayList<>();//用Linked是不是更好 查找快
		//第一步 读取文件，包括log文件 和归属地文件
		//对于登陆数据，不能用Map进行包装。把一次访问数据放在一个Logbean里面
		try (
				BufferedReader bw2 = new BufferedReader(new FileReader("F:\\JAVA视频\\韩利鹏\\day02-app升级\\day03-ip地址归属地\\ip.txt"));
		){
			String line ;
			while((line=bw2.readLine())!=null) {
				String[] sp= line.split("\\|", 9);
				String ipStart = sp[0];
				String ipEnd = sp[1];
				String ipDecimalS = sp[2];
				String ipDecimalE = sp[3];
				String continent = sp[4];
				String country = sp[5];
				String provience = sp[6];
				String city = sp[7];
				LocationBean locationBean = new LocationBean(ipStart,ipEnd,ipDecimalS,ipDecimalE,continent,country,provience,city);
				locat.add(locationBean);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return locat;
	}
	private static List<LogBean> logList() {
		List<LogBean> list = new ArrayList<>();
		//第一步 读取文件，包括log文件 和归属地文件
		//对于登陆数据，不能用Map进行包装。把一次访问数据放在一个Logbean里面
		try (
				BufferedReader bw1 = new BufferedReader(new FileReader("F:\\JAVA视频\\韩利鹏\\day02-app升级\\day03-ip地址归属地\\access.log"));
		){
			String line ;
			/*String[] split = line.split("\\|",9);
			for (String string : split) {
				System.out.println(string);
			}
			String s = "1.0.1.0";
			int transferIp = IpToDecimal.TransferIp(s);
			System.out.println(transferIp);*/
			//ip地址转成十进制或者二进制 先到二进制，然后十进制
			while((line=bw1.readLine())!=null) {
				String[] split = line.split("\\|", 4);
				String logTime = split[0];
				String ip = split[1];
				long ip2 = IpToDecimal.TransferIp(ip);
				String webSite = split[2];
				LogBean lb = new LogBean(logTime,ip2,webSite);
				list.add(lb);
				//只要根据ip去找范围就Ok了
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
