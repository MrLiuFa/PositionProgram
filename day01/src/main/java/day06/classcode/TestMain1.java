package day06.classcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class TestMain1 {

	public static void main(String[] args) {
		//创建一个数组，用于放session和List
		Map<String,List<SessionBean>> sessionId_SessionMap = new HashMap<>();
		//得到Map数组
		Map<String, List<SessionBean>> map = getIp_SessionBean();
		
		Set<Entry<String,List<SessionBean>>> entrySet = map.entrySet();
		for (Entry<String, List<SessionBean>> entry : entrySet) {
			List<SessionBean> value = entry.getValue();
			//根据Map内的List<>数据进行排序
			sortByDate(value);
			
			//对sessionBean的sessionId和order进行赋值
			setSessionBean(value);
			//第一题到此结束
			/*for (SessionBean sessionBean : value) {
				System.out.println(sessionBean);
			}
			System.out.println("----------------");*/
		}
		Set<Entry<String,List<SessionBean>>> entrySet2 = map.entrySet();
		for (Entry<String, List<SessionBean>> entry : entrySet2) {
			List<SessionBean> value = entry.getValue();
			for (SessionBean sessionBean : value) {
				List<SessionBean> list = sessionId_SessionMap.getOrDefault(sessionBean.getSessionId(), new ArrayList<SessionBean>());
				list.add(sessionBean);
				sessionId_SessionMap.put(sessionBean.getSessionId(), list);
			}
		}
		//sessionId_SessionMap中取值
		Set<Entry<String,List<SessionBean>>> entrySet3 = sessionId_SessionMap.entrySet();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
			//System.out.println(sessionId+"\t"+startTime+"\t"+endTime+"\t"+viewTime+"\t"+startWebsit+"\t"+endWebsite );
		// 保存到数据库中和文件中
		/*try (BufferedWriter bw = new BufferedWriter(
				new FileWriter("F:\\JAVA视频\\韩利鹏\\day05-流量统计\\day06-session分析\\result2.txt"));) {

			for (Entry<String, List<SessionBean>> entry : entrySet3) {
				String sessionId = entry.getKey();
				List<SessionBean> value = entry.getValue();
				Date startDate = value.get(0).getDate();
				String startTime = format.format(startDate);
				Date endDate = value.get(value.size() - 1).getDate();
				String endTime = format.format(endDate);
				// 时长 秒
				long viewTime = (endDate.getTime() - startDate.getTime()) / 1000;
				// 起始网页和结束网页
				String startWebsit = value.get(0).getUrl();
				String endWebsite = value.get(value.size() - 1).getUrl();
				String line = sessionId + "\t" + startTime + "\t" + endTime + "\t" + viewTime + "\t" + startWebsit
						+ "\t" + endWebsite;
				bw.write(line);
				bw.newLine();
				bw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		//保存到数据库
		ComboPooledDataSource source = new ComboPooledDataSource();
		QueryRunner runner = new QueryRunner(source);
		String mkDataBase = "create database t_day06 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci";
		String use = "use t_day06";
		String mktable = "create table t_result2_2(No int PRIMARY KEY auto_increment, session varchar(100) ,"
				+ "startTime varchar(50),endTime varchar(50),sufferTime varchar(20),startWebsit varchar(200),endWebsit varchar(200));";
		String sql = "insert into t_result2_2 values(null,?,?,?,?,?,?);";
		try {
			//runner.update(mkDataBase);
			runner.update(use);
			Thread.sleep(1000);
			runner.update(mktable);
			Thread.sleep(1000);
			for (Entry<String, List<SessionBean>> entry : entrySet3) {
				String sessionId = entry.getKey();
				List<SessionBean> value = entry.getValue();
				Date startDate = value.get(0).getDate();
				String startTime = format.format(startDate);
				Date endDate = value.get(value.size() - 1).getDate();
				String endTime = format.format(endDate);
				// 时长 秒
				long viewTime = (endDate.getTime() - startDate.getTime()) / 1000;
				// 起始网页和结束网页
				String startWebsit = value.get(0).getUrl();
				String endWebsite = value.get(value.size() - 1).getUrl() ;
				runner.update(sql,sessionId,startTime,endTime,viewTime,startWebsit,endWebsite);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void setSessionBean(List<SessionBean> value) {
		if (value.size() == 1) {
			String string = UUID.randomUUID().toString().replaceAll("-", "");
			SessionBean sessionBean = value.get(0);
			sessionBean.setSessionId(string);
			sessionBean.setOrder(1);
		} else {
			for (int i = 0; i < value.size() - 1; i++) {
				// 得到相邻的两个sessionBean 判断是否是同一个Session
				SessionBean s1 = value.get(i);
				SessionBean s2 = value.get(i + 1);
				boolean flag = isOneSession(s1, s2);
				if (flag) {// 同一个session
					if (s1.getSessionId() == null) {
						String sessionId = UUID.randomUUID().toString().replaceAll("-", "");
						s1.setSessionId(sessionId);
						s1.setOrder(1);
						s2.setSessionId(sessionId);
						s2.setOrder(2);
					} else {
						s2.setSessionId(s1.getSessionId());
						s2.setOrder(s1.getOrder() + 1);
					}
				} else {// 不同session
					if (s1.getSessionId() == null) {
						String sessionId = UUID.randomUUID().toString().replaceAll("-", "");
						s1.setSessionId(sessionId);
						s1.setOrder(1);
						String sessionId2 = UUID.randomUUID().toString().replaceAll("-", "");
						s2.setSessionId(sessionId2);
						s2.setOrder(1);
					} else {
						String sessionId2 = UUID.randomUUID().toString().replaceAll("-", "");
						s2.setSessionId(sessionId2);
						s2.setOrder(1);
					}
				}
			}
		}

	}

	public static boolean isOneSession(SessionBean sessionBean1, SessionBean sessionBean2) {
		long t1 = sessionBean2.getDate().getTime() - sessionBean1.getDate().getTime();
		if (t1 <= 30 * 60 * 1000) {
			return true;
		}
		return false;
	}

	/**
	 * 根据时间进行排序
	 * 
	 * @param value
	 */
	private static void sortByDate(List<SessionBean> value) {
		Collections.sort(value, new Comparator<SessionBean>() {

			@Override
			public int compare(SessionBean o1, SessionBean o2) {

				return o1.getDate().after(o2.getDate()) ? 1 : -1;
			}
		});

	}

	/**
	 * 读取数据，匹配数据，提取数据，将得到的数据放在一个Map中，返回Map<String,List<SessionBean>>
	 * 
	 * @return
	 */
	private static Map<String, List<SessionBean>> getIp_SessionBean() {
		Map<String, List<SessionBean>> map = new HashMap<>();// 用于存放ip和SessionBean的list数据
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss", Locale.US);
		try (BufferedReader br = new BufferedReader(
				new FileReader("F:\\JAVA视频\\韩利鹏\\day05-流量统计\\day06-session分析\\access.log.fensi"));) {
			String line = null;
			// 194.237.142.21 - - [18/Sep/2013:06:49:18 +0000] "GET
			// /wp-content/uploads/2013/07/rstudio-git3.png HTTP/1.1" 304 0 "-" "Mozilla/4.0
			// (compatible;)"
			while ((line = br.readLine()) != null) {
				String ipRegex = "(\\d+\\.){3}(\\d+)";
				String dateRegex = "\\[(.+)\\d+\\]";
				String urlRegex = "(GET|POST){1}\\s(\\S+)";// 在http之前截断了 会有null值返回
				String ip = getMatcher(ipRegex, line);
				String date = getMatcher(dateRegex, line);
				Date t = sdf.parse(date.substring(1, date.length() - 1));
				// System.out.println(t);
				String url = getMatcher(urlRegex, line);
				if (ip != null && date != null && url != null) {
					SessionBean sessionBean = new SessionBean();
					sessionBean.set(ip, t, url);
					List<SessionBean> list = map.getOrDefault(ip, new ArrayList<SessionBean>());
					list.add(sessionBean);
					map.put(ip, list);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 匹配字符串。得到相应的信息
	 * 
	 * @param regex
	 * @param line
	 * @return
	 */
	private static String getMatcher(String regex, String line) {
		Pattern compile = Pattern.compile(regex);
		Matcher matcher = compile.matcher(line);
		while (matcher.find()) {
			return matcher.group();
		}
		return null;
	}
}
