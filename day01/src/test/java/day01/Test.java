package day01;

import java.util.Date;

import org.junit.After;
import org.junit.Before;

import day06.classcode.SessionBean;
import day06.classcode.TestMain1;

public class Test {
	@Before
	public void say() {
		System.out.println("before.....");
	}
	@org.junit.Test
	public void test() {
		SessionBean s1 = new SessionBean();
		SessionBean  s2 = new SessionBean();
		s1.setDate(new Date());
		s2.setDate(new Date(new Date().getTime()+1000*60*30));
		boolean oneSession = TestMain1.isOneSession(s1,s2);
		System.out.println(oneSession);
	}
	@After
	public void saybye() {
		System.out.println("after.......");
	}
	
}
