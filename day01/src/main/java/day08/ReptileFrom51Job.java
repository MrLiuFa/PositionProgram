package day08;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ReptileFrom51Job {

	public static void main(String[] args) {
		//String url = "https://search.51job.com/list/040000,000000,0000,00,9,99,%25E5%25A4%25A7%25E6%2595%25B0%25E6%258D%25AE,2,1.html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
		String url = "https://search.51job.com/list/120300,000000,0000,00,9,99,%25E5%25A4%25A7%25E6%2595%25B0%25E6%258D%25AE,2,1.html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
		Document document = getDocument(url);
		Page page = new Page();
		page.setDocument(document);
		//从page中得到需要的数据
		int count = 0;
		while (true) {
			System.out.println("第"+(++count)+"页");
			List<Job> jobByJob = getJobByPage(page);
			for (Job job : jobByJob) {
				System.out.println(job);
			}
			findNextPage(page);
			if (page.isHasNextPage()) {
				url = page.getNextUrl();
				document = getDocument(url);
				page.setDocument(document);
			}else {
				break;
			}
		}
	}

	private static void findNextPage(Page page) {
		Document document = page.getDocument();
		Elements nextPageElement = document.select(".bk");
		Elements nextPageElement2 = nextPageElement.get(1).select("a");
		if (nextPageElement2!=null&&nextPageElement2.size()>0) {
			String nextUrl = nextPageElement2.attr("href");
			System.out.println(nextUrl);
			page.setNextUrl(nextUrl);
			page.setHasNextPage(true);
		}else {
			page.setHasNextPage(false);
		}
	}

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
