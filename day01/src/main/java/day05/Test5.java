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
 * 根据给的手机号段归属地规则，计算出总流量最高的市Top3
 */
public class Test5 {

	public static void main(String[] args) {
		Map<String, String> cityMap = getCityceMap();
		
		Map<String,Long> map = new HashMap<>();//城市--流量
		try (BufferedReader br = new BufferedReader(
				new FileReader("F:\\JAVA视频\\韩利鹏\\day04-电影排行\\day05-流量统计\\http.log"));
				) {
			String line = null;
			while ((line = br.readLine()) != null) {
				String pNum = line.split("\t")[0].substring(0, 7);
				String[] split = line.split("\t")[1].split(" ");
				Long up = Long.parseLong(split[1]);
				Long down = Long.parseLong(split[2]);
				String city = cityMap.get(pNum);
				
				Long sum = map.getOrDefault(city, 0L);
				sum = sum + up + down;
				map.put(city, sum);// 相应数据放入Map中
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Set<Entry<String,Long>> set = map.entrySet();
		ArrayList<Entry<String,Long>> list = new ArrayList<>(set);
		Utils.sortByFlux(list);
		
		try (FileWriter fw2 = new FileWriter("F:\\JAVA视频\\韩利鹏\\day04-电影排行\\day05-流量统计\\httpwork5.txt");) {

			String title = "城市" + "\t" + "流量" + "\n";
			fw2.write(title);
			for (Entry<String, Long> entry : list) {
				String s = entry.getKey() + "\t" + entry.getValue() + "\n";
				fw2.write(s);
				// System.out.println(entry.getKey()+"\t"+entry.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Map<String,String> getCityceMap() {
		//读取数据
		Map<String,String> cityMap = new HashMap<>();
		try (BufferedReader br = new BufferedReader(new FileReader("F:\\JAVA视频\\韩利鹏\\day04-电影排行\\day05-流量统计\\phone.txt"));){
			String line = null;
			br.readLine();//读取首行，不需要的数据跳过，不处理
			while ((line=br.readLine())!=null) {
				String[] split = line.split("\\s");
				//System.out.println(split[1]);
				String preSevenNum = split[1];
				String city = split[3];
				//System.out.println(province);
				cityMap.put(preSevenNum, city);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cityMap;
	}

}
