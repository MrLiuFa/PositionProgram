package day07;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import com.alibaba.fastjson.JSON;



public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String ,String> map = new HashMap<>();
		map.put("100", "aha");
		map.put("sda", "ds");
		String string = map.get("hahdab");
		//System.out.println(string);
		
		String uri = "http://gc.ditu.aliyun.com/regeocoding?l=41.083778,113.983896&type=010";
		HttpClient client = new HttpClient();
		HttpMethod method = new GetMethod(uri);
		try {
			client.executeMethod(method);
			String asString = method.getResponseBodyAsString();
			
			System.out.println(asString);
			
			NetJson netLocal = JSON.parseObject(asString, NetJson.class);
			double[] queryLocation = netLocal.getQueryLocation();
			Addr[] ddrList = netLocal.getAddrList();
			double latitude = queryLocation[0];
			double longitude = queryLocation[1];
			String geoHash = UtilsClass.getGeoHash(latitude, longitude);
			Addr addr = ddrList[0];
			String admName = addr.getAdmName();
			String[] split = admName.split(",");
			String province = split[0];
			String city = split[1];
			String district = split[2];
			String name = addr.getName();
			String result = geoHash+"\t"+province+"\t"+city+"\t"+district+"\t"+name;
			System.out.println(result);
			
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
