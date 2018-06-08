package day07;

import java.util.Arrays;

public class NetJson {
	private double[] queryLocation;
	private Addr[] addrList;
	
	
	
	public double[] getQueryLocation() {
		return queryLocation;
	}



	public void setQueryLocation(double[] queryLocation) {
		this.queryLocation = queryLocation;
	}



	public Addr[] getAddrList() {
		return addrList;
	}



	public void setAddrList(Addr[] addrList) {
		this.addrList = addrList;
	}



	@Override
	public String toString() {
		return "NetJson [queryLocation=" + Arrays.toString(queryLocation) + ", addrList=" + Arrays.toString(addrList)
				+ "]";
	}
	
	

}
