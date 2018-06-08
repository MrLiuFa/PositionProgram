package day01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 获得好友数量并按降序排列
 * @author Administrator
 *
 */
public class Test1_2 {

	public static void main(String[] args) {
		Map<String,Integer> countMap = getCountMap();//获得了名字和好友数量 下面是完成排序的过程
		List<CountBean> list = new ArrayList<>();
		Set<Entry<String,Integer>> entrySet = countMap.entrySet();
		for (Entry<String, Integer> entry : entrySet) {
			CountBean c = new CountBean(entry.getKey(),entry.getValue());
			list.add(c);
		}
		Collections.sort(list, new Comparator<CountBean>() {

			@Override
			public int compare(CountBean o1, CountBean o2) {
				
				return o2.getCount()-o1.getCount();
			}
		});
		Iterator<CountBean> it = list.iterator();
		while(it.hasNext())
			System.out.println(it.next());//打印的是countBean默认调用toString方法
		
		
		//写入数据库
		ComboPooledDataSource dataSource = new ComboPooledDataSource(); 
		String sql = "Insert into t_count values(?,?);";
		QueryRunner runner = new QueryRunner(dataSource);
		
		try {
			for(int i=0;i<list.size();i++) {
				String uname = list.get(i).getName();
				int count = list.get(i).getCount();
				runner.update(sql, uname,count);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static Map<String,Integer> getCountMap() {
		Map<String,Integer> map = new HashMap<>();
		try (
				BufferedReader br = new BufferedReader(new FileReader("F:\\JAVA视频\\赵念行\\好友.txt"));
				){
			String readLine = null;
			while ((readLine=br.readLine())!=null) {
				//System.out.println(readLine);
				String[] split = readLine.split(":");
				String key = split[0];
				int length = split[1].split(",").length;
				map.put(key, length);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}
