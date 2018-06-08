package day03.classDemo;

public class IpBean {
	private String ipStart;
	private String ipEnd;
	private Long ipDecStart;
	private Long ipDecEnd;
	private String province;
	private String city;
	
	
	
	public IpBean() {
		super();
	}
	public IpBean(String ipStart, String ipEnd, Long ipDecStart, Long ipDecEnd, String province, String city) {
		super();
		this.ipStart = ipStart;
		this.ipEnd = ipEnd;
		this.ipDecStart = ipDecStart;
		this.ipDecEnd = ipDecEnd;
		this.province = province;
		this.city = city;
	}
	public String getIpStart() {
		return ipStart;
	}
	public void setIpStart(String ipStart) {
		this.ipStart = ipStart;
	}
	public String getIpEnd() {
		return ipEnd;
	}
	public void setIpEnd(String ipEnd) {
		this.ipEnd = ipEnd;
	}
	public Long getIpDecStart() {
		return ipDecStart;
	}
	public void setIpDecStart(Long ipDecStart) {
		this.ipDecStart = ipDecStart;
	}
	public Long getIpDecEnd() {
		return ipDecEnd;
	}
	public void setIpDecEnd(Long ipDecEnd) {
		this.ipDecEnd = ipDecEnd;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "IpBean [ipStart=" + ipStart + ", ipEnd=" + ipEnd + ", ipDecStart=" + ipDecStart + ", ipDecEnd="
				+ ipDecEnd + ", province=" + province + ", city=" + city + "]";
	}
	
	

}
