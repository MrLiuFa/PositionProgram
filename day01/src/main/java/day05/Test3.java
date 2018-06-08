package day05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * 3.根据给的手机号段运营商规则，计算出总流量最高的运营商Top3
 * 
 * @author Administrator
 *
 */
public class Test3 {

	public static void main(String[] args) {

		Map<String, String> servertMap = getServertMap();

		Map<String, Long> map = new HashMap<>();// 省份--流量
		try (BufferedReader br = new BufferedReader(
				new FileReader("F:\\JAVA视频\\韩利鹏\\day04-电影排行\\day05-流量统计\\http.log"));) {
			String line = null;
			while ((line = br.readLine()) != null) {
				String pNum = line.split("\t")[0].substring(0, 7);
				String[] split = line.split("\t")[1].split(" ");
				Long up = Long.parseLong(split[1]);
				Long down = Long.parseLong(split[2]);
				String servert = servertMap.get(pNum);
				Long sum = map.getOrDefault(servert, 0L);
				sum = sum + up + down;
				map.put(servert, sum);// 相应数据放入Map中
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Set<Entry<String, Long>> set = map.entrySet();
		ArrayList<Entry<String, Long>> list = new ArrayList<>(set);
		Utils.sortByFlux(list);
		
		try (FileWriter fw2 = new FileWriter("F:\\JAVA视频\\韩利鹏\\day04-电影排行\\day05-流量统计\\httpwork3.txt");) {

			String title = "运营商" + "\t" + "流量" + "\n";
			fw2.write(title);
			for (Entry<String, Long> entry : list) {
				String s = entry.getKey() + "\t" + entry.getValue() + "\n";
				fw2.write(s);
				 //System.out.println(entry.getKey()+"\t"+entry.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Map<String, String> getServertMap() {
		// 读取数据
		Map<String, String> servertMap = new HashMap<>();
		try (BufferedReader br = new BufferedReader(
				new FileReader("F:\\JAVA视频\\韩利鹏\\day04-电影排行\\day05-流量统计\\phone.txt"));) {
			String line = null;
			br.readLine();// 读取首行，不需要的数据跳过，不处理
			while ((line = br.readLine()) != null) {
				String[] split = line.split("\\s");
				// System.out.println(split[1]);
				String preSevenNum = split[1];
				String severt = split[4];
				//System.out.println(severt);
				servertMap.put(preSevenNum, severt);
			}
			// 检验provinceMap
			/*
			 * Set<Entry<String,String>> set = provinceMap.entrySet(); for (Entry<String,
			 * String> entry : set) {
			 * System.out.println(entry.getKey()+"\t"+entry.getValue()); }
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		return servertMap;
	}

}
