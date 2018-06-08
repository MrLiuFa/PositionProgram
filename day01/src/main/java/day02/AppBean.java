package day02;

public class AppBean {
	private String date ;
	private String userName ;
	private String appName ;
	private String appStore;
	private String lowVersion ;
	private String upVersion ;
	
	public AppBean() {
		super();
	}
	
	public AppBean(String date, String userName, String appName, String appStore, String lowVersion, String upVersion) {
		super();
		this.date = date;
		this.userName = userName;
		this.appName = appName;
		this.appStore = appStore;
		this.lowVersion = lowVersion;
		this.upVersion = upVersion;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppStore() {
		return appStore;
	}
	public void setAppStore(String appStore) {
		this.appStore = appStore;
	}
	public String getLowVersion() {
		return lowVersion;
	}
	public void setLowVersion(String lowVersion) {
		this.lowVersion = lowVersion;
	}
	public String getUpVersion() {
		return upVersion;
	}
	public void setUpVersion(String upVersion) {
		this.upVersion = upVersion;
	}
	@Override
	public String toString() {
		return "AppBean [date=" + date + ", userName=" + userName + ", appName=" + appName + ", appStore=" + appStore
				+ ", lowVersion=" + lowVersion + ", upVersion=" + upVersion + "]";
	}
	

}
