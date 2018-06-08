package day06.classcode;

import java.util.Date;
/**
 * 封装session数据
 * @author Administrator
 *
 */
public class SessionBean {
	
	private String sessionId;
	private String ip;
	private Date date;
	private String url;
	private int order;
	
	
	
	public SessionBean() {
		super();
	}
	public void set(String ip, Date date, String url) {
		this.ip = ip;
		this.date = date;
		this.url = url;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	@Override
	public String toString() {
		return "SessionBean [sessionId=" + sessionId + ", ip=" + ip + ", date=" + date + ", url=" + url + ", order="
				+ order + "]";
	}
	
	
	

}
