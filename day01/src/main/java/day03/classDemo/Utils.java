package day03.classDemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;

public class Utils {
	/**
	 * 包括把用户ip转化为十进制的transUserIpToDecIp方法
	 * 根据ip查找归属地的方法。二分查找
	 */
	//把字符串IP地址转化为长整型的十进制整数
	
	static List<IpBean> ipLocal = null;
	static {
		ipLocal = IpLocal.getIpLocal();
	}
	public static Long transUserIpToDecIp(String ip) {
		Long ipLong = 0L;
		String[] sections = ip.split("\\.");
		int i = 3;  
        for (String str : sections) {  
        	ipLong += (Long.parseLong(str) << (i * 8));  
            i--;  
        }  
		return ipLong;
	}
	/**
	 * 二分查找ip地址对应 的归属地
	 * @param ip
	 * @return
	 */
	public static IpBean SearchIpByBinary(Long ip) {
		int start = 0;
		int end = ipLocal.size()-1;
		while(start<=end) {
			
			int middle = (start+end)/2;
			IpBean ipBean = ipLocal.get(middle);
			if(ip>ipBean.getIpDecEnd()) {
				start = middle + 1;
			}else if(ip<ipBean.getIpDecStart()) {
				end = middle -1 ;
			}else {
				return ipBean;
			}
		}
		return null;
	}
	public static void sort(ArrayList<Entry<String,Integer>> arrayList ) {
		Collections.sort(arrayList, new Comparator<Entry<String,Integer>>() {

			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				
				return o1.getValue()-o2.getValue();
			}
		});
	}

}
