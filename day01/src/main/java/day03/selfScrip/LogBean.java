package day03.selfScrip;

public class LogBean {
	private String logTime;
	private long ip;
	private String webSite;
	
	
	public LogBean() {
		super();
	}
	public LogBean(String logTime, long ip, String webSite) {
		super();
		this.logTime = logTime;
		this.ip = ip;
		this.webSite = webSite;
	}
	
	public String getLogTime() {
		return logTime;
	}
	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}
	public long getIp() {
		return ip;
	}
	public void setIp(int ip) {
		this.ip = ip;
	}
	public String getWebSite() {
		return webSite;
	}
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	@Override
	public String toString() {
		return "LogBean [logTime=" + logTime + ", ip=" + ip + ", webSite=" + webSite + "]";
	}
	
	

}
