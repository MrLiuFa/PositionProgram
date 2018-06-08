package day05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Test1 {

	public static void main(String[] args) {
		
		Map<String,Long> map = new HashMap<>();
		//根据给的用户上网日志记录数据，计算出总流量最高的网站Top3(网站例如：v.baidu.com，weibo.com)
		try (
				BufferedReader br = new BufferedReader(new FileReader("F:\\JAVA视频\\韩利鹏\\day04-电影排行\\day05-流量统计\\http.log"));
				FileWriter fw = new FileWriter("F:\\JAVA视频\\韩利鹏\\day04-电影排行\\day05-流量统计\\httpwork1.txt");
				){
			String line = null;
			while((line=br.readLine())!=null) {
				String[] split = line.split("\t")[1].split(" ");
				String oldUrl = split[0];
				Long up = Long.parseLong(split[1]);
				Long down = Long.parseLong(split[2]);
				//对oldURL进行匹配
				String url = getUrl(oldUrl);
				//System.out.println(url);
				Long sum = map.getOrDefault(url, 0L);
				sum = sum + up +down;
				map.put(url, sum);//相应数据放入Map中
			}
			//对Map进行排序
			Set<Entry<String,Long>> set = map.entrySet();
			ArrayList<Entry<String,Long>> list = new ArrayList<>(set);
			Utils.sortByFlux(list);
			//保存到文件中
			
			for (int i=0;i<Math.min(3, list.size());i++) {
				Entry<String, Long> entry = list.get(i);
				String website = entry.getKey();
				Long flux = entry.getValue();
				String s = "website:"+website+"   "+"流量："+flux+"\n";
				fw.write(s);
				
				//System.out.println(entry.getKey()+"\t\t"+entry.getValue());
			}
			fw.flush();
			/*for (Entry<String, Long> entry : list) {
				System.out.println(entry.getKey()+"\t\t"+entry.getValue());
			}*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static String getUrl(String url) {
		Pattern compile = Pattern.compile("(\\w+\\.)?(\\w+\\.){1}(\\w+)");
		Matcher matcher = compile.matcher(url);
		while(matcher.find()) {
			return matcher.group();
		}
		
		return null;
	}

}
