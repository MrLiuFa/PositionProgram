package day02;
/**
 * 统计版本更新
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Test1 {

	public static void main(String[] args) throws Exception {
		
		Map<String, List<String>> map = getMap();
		
		//对list进行排序,  
		Set<String> keySet = map.keySet();
		List<AppBean> result = new ArrayList<>();
		for (String string : keySet) {
			
			List<String> list = map.get(string);
			SortUtils.sortByVersion(list);//完成version的排序过程
			
			//对String进行切分,并封装到一个List中
			String[] mapString = string.split(",");
			String date = mapString[0];
			String userName = mapString[1];
			String appName = mapString[2];
			String appStore = mapString[3];
			if(list.size()>1) {
				String lowVersion = list.get(0);
				String upVersion = list.get(list.size()-1);
				AppBean appBean = new AppBean(date,userName,appName,appStore,lowVersion,upVersion);
				result.add(appBean);
			}
		}
		//按时间进行更新排序
		SortUtils.sortByTime(result);
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("F:\\JAVA视频\\韩利鹏\\appResult.txt"));
			){
				for (AppBean appBean : result) {
					bw.write(appBean.toString());
					bw.newLine();
				}
				bw.flush();
		}catch(Exception e) {
			e.printStackTrace();
		}
		//保存数据
	}

	private static Map<String,List<String>> getMap() {
		Map<String,List<String>> map = new HashMap<>();
		try (BufferedReader br = new BufferedReader(new FileReader("F:\\JAVA视频\\韩利鹏\\app.txt"));
				){
			// 1、读取数据
			// 2 拆分数据
			String line;
			while ((line=br.readLine())!=null) {
				String[] split = line.split(",");
				String date = split[0];
				String userName = split[1];
				String appName = split[2];
				String appStore = split[3];
				String version = split[5];
				
				//3 组合key
				String key = date + ","+userName+","+ appName+","+appStore;
				//4  往map里面添加数据 如果有key 则在list末尾加入，否则创建新的list
				if (map.containsKey(key)) {
					List<String> list = map.get(key);
					list.add(version);
					map.put(key, list);
				}else {
					List<String> list = new ArrayList<>();
					list.add(version);
					map.put(key, list);
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}
