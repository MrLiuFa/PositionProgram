package day04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.alibaba.fastjson.JSON;

/**
 * 
		1.每个用户评分最高的3部电影评分信息
		2.每个用户的uid和评分的平均值
		3.最大方(评分平均值高)的3个用户的uid和评分平均值
		4.最热门的3部电影id和评价次数
		5.评价最高的3部电影id和评分均值

 * @author Administrator
 *
 */
public class MovieRate {
	

	public static void main(String[] args) {
		Map<Integer, List<MovieJson>> mM = getMovieMap();
		
/*---------------------------------------------------------------------*/	
		//得到values,把values转成一个集合，自定义排序方法。
		Collection<List<MovieJson>> values = mM.values();
		//1.每个用户评分最高的3部电影评分信息
		/*for (List<MovieJson> list : values) {
			SortUtils.sortList(list);//对List降序排列
			MovieJson movieJson1 = list.get(0);
			MovieJson movieJson2 = list.get(1);
			MovieJson movieJson3 = list.get(2);
			System.out.println(movieJson1);
			System.out.println(movieJson2);
			System.out.println(movieJson3);
			System.out.println("-----------------------------");
		}*/
/*---------------------------------------------------------------------*/	
		//2 每个用户的uid和评分的平均值
		/*Set<Integer> keySet = mM.keySet();
		//创建一个Map存放uid和平均值
		Map<Integer,Double> aveRateMap = new HashMap<>();
		
		for (Integer key : keySet) {
			List<MovieJson> list = mM.get(key);
			double average = SortUtils.average(list);//每个用户的平均评分
			
			aveRateMap.put(key, average);//放进Map中
			//System.out.println(key+" ,"+average );
		}*/
/*---------------------------------------------------------------------*/
		//3.最大方(评分平均值高)的3个用户的uid和评分平均值
		/*Set<Entry<Integer,Double>> entrySet = aveRateMap.entrySet();
		
		//完成对平均值的排序
		List<Entry<Integer, Double>> sortMap = SortUtils.sortMap(entrySet);
		//打印前三个
		int i = 0;
		for (Entry<Integer, Double> entry : sortMap) {
			System.out.println(entry.getKey()+" "+entry.getValue());
			i++;
			if (i>2) {
				break;
			}
		}*/
		
/*---------------------------------------------------------------------*/
		//4.最热门的3部电影id和评价次数  评价次数最高的三部电影。
		Map<String, Integer> HotestMovie = new HashMap<>();
		//获得平均评分和电影名字的Map
		Map<String, Double> MovieRate = new HashMap<>();
		
		Collection<List<MovieJson>> values2 = mM.values();
		
		for (List<MovieJson> list : values2) {
			for (MovieJson movieJson : list) {
				String movie = movieJson.getMovie();//获得电影名字
				int rate2 = movieJson.getRate();
				//如果电影名字存在，则加一，不然就是0；
				Integer count = HotestMovie.getOrDefault(movie,0);
				Double rate = MovieRate.getOrDefault(movie,0.0);//电影名称  ---平均值
				count++;
				rate = (rate*(count-1)+rate2)/count;
				HotestMovie.put(movie, count);//最热门电影
				MovieRate.put(movie, rate);//电影的平均评分
			}
		}
		//对HotestMovie进行排序
		Set<Entry<String,Integer>> entrySet2 = HotestMovie.entrySet();
		List<Entry<String,Integer>> list = new ArrayList<>(entrySet2 ); 
		SortUtils.sortHotMovie(list);
		int j = 0;
		for (Entry<String, Integer> entry : list) {
			System.out.println(entry.getKey()+"\t"+entry.getValue());
			j++;
			if (j>2) {
				break;
			}
		}
		
/*---------------------------------------------------------------------*/
		//5.评价最高的3部电影id和评分均值  平均均值在第4部分计算
		Set<Entry<String,Double>> entrySet = MovieRate.entrySet();
		ArrayList<Entry<String,Double>> arrayList = new ArrayList<>(entrySet);
		SortUtils.sortRate(arrayList);
		int k = 0 ;
		for (Entry<String, Double> entry : arrayList) {
			System.out.println(entry.getKey()+"\t"+entry.getValue());
			k++;
			if (k>2) {
				break;
			}
		}
		
	}

	private static Map<Integer,List<MovieJson>> getMovieMap() {
		
		Map<Integer,List<MovieJson>> map = new HashMap<>();
		try (
				BufferedReader bw = new BufferedReader(new FileReader("F:\\JAVA视频\\韩利鹏\\day04-电影排行\\rating.txt"));
				){
			String line;
			while ((line=bw.readLine())!=null) {
				//把所有json文件转化为对象。
				MovieJson parseObject = JSON.parseObject(line, MovieJson.class);
				int uid = parseObject.getUid();
				if (map.containsKey(uid)) {
					List<MovieJson> list = map.get(uid);
					list.add(parseObject);
				}else {
					List<MovieJson> list = new ArrayList<>();
					list.add(parseObject);
					map.put(parseObject.getUid(),list);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}
