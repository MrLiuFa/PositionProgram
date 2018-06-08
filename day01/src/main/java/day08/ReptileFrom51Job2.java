package day08;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.dbutils.QueryRunner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ReptileFrom51Job2 {
	public static void main(String[] args) {
		String url = "https://search.51job.com/list/120300,000000,0000,00,9,99,%25E5%25A4%25A7%25E6%2595%25B0%25E6%258D%25AE,2,1.html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
		int count = 0;
		Page page = new Page();
		//存放到数据库 ----修改c3p0配置文件选择数据库
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		QueryRunner runner = new QueryRunner(dataSource);
		//创建表格
		String mktable = "create table t_jobtable(No Int primary key AUTO_increment,JobName varchar(50),company varchar(30),address varchar(20),salary varchar(20),date varchar(20));";
		String sql ="insert into t_jobtable values(null,?,?,?,?,?)";
		try {
			runner.update(mktable);
			Thread.sleep(1000);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		do {
			System.out.println("第"+(++count)+"页");
			Document document = getDocument(url);
			page.setDocument(document);
			//得到当前Page下的job信息
			List<Job> jobByJob = getJobByPage(page);//每一页一个List
			for (Job job : jobByJob) {
				String jobName = job.getJobName();
				String company = job.getCompany();
				String address = job.getAddress();
				String salary = job.getSalary();
				String date = job.getDate();
				try {
					runner.update(sql, jobName,company,address,salary,date);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//查询是否有下一页
			findNextPage(page);//完成了对page的赋值操作
			if (page.isHasNextPage()) {
				url = page.getNextUrl();
			}else {
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}while (true);
	}
/**
 * 通过查询网页源代码，查看是否有下一页，如果有,获得下一页的网址
 * @param page
 */
	private static void findNextPage(Page page) {
		Document document = page.getDocument();
		Elements nextPageElement = document.select(".bk");
		Elements nextPageElement2 = nextPageElement.get(1).select("a");
		if (nextPageElement2!=null&&nextPageElement2.size()>0) {
			String nextUrl = nextPageElement2.attr("href");
			page.setNextUrl(nextUrl);
			page.setHasNextPage(true);
		}else {
			page.setHasNextPage(false);
		}
	}
/**
 * 通过网页源代码获得当前页的工作描述信息，返回一个当前页的List<Job>
 * @param page
 * @return
 */
	private static List<Job> getJobByPage(Page page) {
		
		List<Job> list = new ArrayList<>();//每次都new了一个List 
		Document document = page.getDocument();
		Elements select = document.select("#resultList .el");//锁定的id的内容
		select.remove(0);
		for (Element element : select) {
			//得到jobName
			//String jobName = element.select(".t1").text();
			Elements jobElements = element.select(".t1 a");
			String jobName = jobElements.attr("title");
			//得到company
			Elements companyElements = element.select(".t2 a");
			String company = companyElements.attr("title");
			//得到address
			String address = element.select(".t3").text();
			//得到salary
			String salary = element.select(".t4").text();
			//得到date
			String date = element.select(".t5").text();
			Job job = new Job();
			job.set(jobName, company, address, salary, date);
			list.add(job);
			//System.out.println(date);
			
		}
		return list;
		
	}
/**
 * 通过给定的url，得到网页源代码
 * @param url
 * @return
 */
	private static Document getDocument(String url) {
		Document document = null;
		try {
			document = Jsoup.parse(new URL(url), 1000);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return document;
	}
}
