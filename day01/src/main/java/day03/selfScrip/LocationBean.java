package day03.selfScrip;

public class LocationBean {
	private String ipStart ;
	private String ipEnd ;
	private String ipDecimalS;
	private String ipDecimalE;
	private String continent;
	private String country ;
	private String provience;
	private String city;
	public LocationBean() {
		super();
	}
	public LocationBean(String ipStart, String ipEnd, String ipDecimalS, String ipDecimalE, String continent,
			String country, String provience, String city) {
		super();
		this.ipStart = ipStart;
		this.ipEnd = ipEnd;
		this.ipDecimalS = ipDecimalS;
		this.ipDecimalE = ipDecimalE;
		this.continent = continent;
		this.country = country;
		this.provience = provience;
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
	public String getIpDecimalS() {
		return ipDecimalS;
	}
	public void setIpDecimalS(String ipDecimalS) {
		this.ipDecimalS = ipDecimalS;
	}
	public String getIpDecimalE() {
		return ipDecimalE;
	}
	public void setIpDecimalE(String ipDecimalE) {
		this.ipDecimalE = ipDecimalE;
	}
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvience() {
		return provience;
	}
	public void setProvience(String provience) {
		this.provience = provience;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "LocationBean [ipDecimalS=" + ipDecimalS + ", ipDecimalE=" + ipDecimalE + ", provience=" + provience
				+ ", city=" + city + "]";
	}
	

}
