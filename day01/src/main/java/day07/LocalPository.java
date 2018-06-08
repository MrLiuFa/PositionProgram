package day07;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class LocalPository {

	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream
				("F:\\JAVA视频\\韩利鹏\\day06-session分析\\day07-单车定位\\bj-poi-1.csv"),"gbk"));
				BufferedWriter bw = new BufferedWriter(new FileWriter("F:\\JAVA视频\\韩利鹏\\day06-session分析\\day07-单车定位\\csv_result.tsv"));){
			String line = null;
			while ((line=br.readLine())!=null) {
				//System.out.println(line);
				try {
					String[] split = line.split(",");
					String name = split[0];
					double locationx = Double.parseDouble(split[2]);//经度
					double locationy = Double.parseDouble(split[3]);//纬度
					String city = split[5];
					String district = split[7];
					//System.out.println(district);
					//将经纬度坐标转化为geohash
					String geoHash = UtilsClass.getGeoHash(locationy, locationx);
					String result = geoHash+"\t"+"北京"+"\t"+city+"\t"+district+"\t"+name;
					//System.out.println(result);
					bw.write(result);//写入文件
					bw.newLine();
				} catch (Exception e) {
					continue;
				}
				bw.flush();
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
