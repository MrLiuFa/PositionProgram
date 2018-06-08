package day07;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.alibaba.fastjson.JSON;

import ch.hsr.geohash.GeoHash;

public class UtilsClass {
	/**
	 * 
	 * @param latitude--纬度
	 * @param longitude--经度
	 * @return
	 */
	public static String getGeoHash(double latitude,double longitude) {
		String geoBase32 = GeoHash.withCharacterPrecision(latitude, longitude, 8).toBase32();
		return geoBase32;
	}
	/**
	 * 
	 * @param jsonString Json格式的字符串
	 * @return 需要格式的字符串
	 * @throws IOException
	 */
	public static String jsonToAddr(String jsonString) throws IOException {
		NetJson netLocal = JSON.parseObject(jsonString, NetJson.class);
		double[] queryLocation = netLocal.getQueryLocation();
		Addr[] ddrList = netLocal.getAddrList();
		double latitude = queryLocation[0];
		double longitude = queryLocation[1];
		String geoHash = getGeoHash(latitude, longitude);
		Addr addr = ddrList[0];
		String admName = addr.getAdmName();
		String[] split = admName.split(",");
		if (split.length>2) {
			BufferedWriter bw = new BufferedWriter(new FileWriter("F:\\JAVA视频\\韩利鹏\\day06-session分析\\day07-单车定位\\csv_result.tsv",true));
			String province = split[0];
			String city = split[1];
			String district = split[2];
			String name = addr.getName();
			String result = geoHash+"\t"+province+"\t"+city+"\t"+district+"\t"+name;
			bw.write(result);
			System.err.println(result);
			bw.newLine();
			bw.flush();
			bw.close();
			return result;
		}
		return "此地址暂缺，请您帮助补充他";
		
	}

}
