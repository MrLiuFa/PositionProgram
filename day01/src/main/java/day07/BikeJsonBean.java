package day07;

public class BikeJsonBean {
	private String event_type;
	private String page;
	private String time;
	private String uid;
	private String longitude;
	private String latitude;
	private String province;
	private String city;
	private String district;
	
	public String getEvent_type() {
		return event_type;
	}
	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
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
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public BikeJsonBean(String event_type, String page, String time, String uid, String longitude, String latitude,
			String province, String city, String district) {
		super();
		this.event_type = event_type;
		this.page = page;
		this.time = time;
		this.uid = uid;
		this.longitude = longitude;
		this.latitude = latitude;
		this.province = province;
		this.city = city;
		this.district = district;
	}
	public BikeJsonBean() {
		super();
	}
	@Override
	public String toString() {
		return "BikeJsonBean [event_type=" + event_type + ", page=" + page + ", time=" + time + ", uid=" + uid
				+ ", longitude=" + longitude + ", latitude=" + latitude + ", province=" + province + ", city=" + city
				+ ", district=" + district + "]";
	}
	
	

}
