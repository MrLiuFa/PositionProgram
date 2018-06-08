package day04.teacherprogram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * 对源文件进行读取，存储到一个List中。方便其他使用中调用。
 * @author Administrator
 *
 */
public class MovieShared {
	public static List<MovieBean> getMovieBean(){
		List<MovieBean> list = new ArrayList<>();

		try (
				BufferedReader br = new BufferedReader(new FileReader("F:\\JAVA视频\\韩利鹏\\day04-电影排行\\rating.txt"));
				){
			String line = null;
			while ((line=br.readLine())!=null) {
				MovieBean movieBean = JSON.parseObject(line, MovieBean.class);
				list.add(movieBean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	

}
