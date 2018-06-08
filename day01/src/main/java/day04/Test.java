package day04;

import com.alibaba.fastjson.JSON;

public class Test {

	public static void main(String[] args) {
		String line = "{\"movie\":\"223\",\"rate\":\"5\",\"timeStamp\":\"974700011\",\"uid\":\"1828\"}";
		MovieJson parseObject = JSON.parseObject(line, MovieJson.class);
		System.out.println(parseObject);
		
		
		String jsonString = JSON.toJSONString(parseObject);
		System.out.println(jsonString);
	}

}
