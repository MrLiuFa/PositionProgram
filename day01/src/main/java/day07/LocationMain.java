package day07;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import com.alibaba.fastjson.JSON;

public class LocationMain {
	static Map<String,String> map = null;
	static {
		map = getLocalMap();//map只加载一次
	}

	public static void main(String[] args) {
		
		//把bike数据转化为可读数据
		try (BufferedReader br = new BufferedReader(new FileReader("F:\\JAVA视频\\韩利鹏\\day06-session分析\\day07-单车定位\\bikes.log"));
				BufferedWriter bw = new BufferedWriter(new FileWriter("F:\\JAVA视频\\韩利鹏\\day06-session分析\\day07-单车定位\\location.txt"))){
			String line = null;
			while ((line=br.readLine())!=null) {
				BikeJsonBean bean = JSON.parseObject(line, BikeJsonBean.class);
				//System.out.println(bean);
				String latitude = bean.getLatitude();
				String longitude = bean.getLongitude();
				//经纬度转geohash
				double lat = Double.parseDouble(latitude);//纬度
				double lng = Double.parseDouble(longitude);
				
				//去本地文件中查找，没找到就去网络上查找
				String addr = FindLocation(lat,lng);
				if (!"此地址暂缺，请您帮助补充他".equals(addr)) {
					bw.write(addr);
					bw.newLine();
					bw.flush();
				}
				System.out.println(addr);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
/**
 * 通过阿里云查找经纬度对应的地址
 * @param lat--纬度
 * @param lng--经度
 * @return--Json格式的表示地址的数据
 */
	private static String findByInternet(double lat, double lng) {
		
		String uri = "http://gc.ditu.aliyun.com/regeocoding?l=" + lat + "," + lng+"&type=010";
		HttpClient client = new HttpClient();
		HttpMethod method = new GetMethod(uri);
		try {
			client.executeMethod(method);
			String string = method.getResponseBodyAsString();
			return string;
			
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	
	}
/**
 * 
 * @param lat  纬度
 * @param lng  经度
 * @return 查找的地址结果
 * @throws IOException
 */
	private static String FindLocation(double lat, double lng) throws IOException {
		String location = null;
		String geoHash = UtilsClass.getGeoHash(lat, lng);
		location  = map.get(geoHash);
		if (location==null) {
			String Netjson = findByInternet(lat,lng);
			location  = UtilsClass.jsonToAddr(Netjson);
			map.put(geoHash, location);
		}
		return location;
	}
/**
 * 		获得本地Map。用于存放geoHash和对应的地址
 * @return Map<String,String>
 */
	private static Map<String,String> getLocalMap() {
		//把数据加载进来，形成Map形式的
		Map<String,String> map = new HashMap<>();
		try (	BufferedReader br = new BufferedReader(new FileReader("F:\\JAVA视频\\韩利鹏\\day06-session分析\\day07-单车定位\\csv_result.tsv"));){
			String line = null;
			while ((line=br.readLine())!=null) {
				//System.out.println(line);
				String[] split = line.split("\t");
				String geoHash = split[0];
				map.put(geoHash, line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
