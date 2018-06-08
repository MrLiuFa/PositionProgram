package day03.selfScrip;

public class IpToDecimal {
	public static long TransferIp(String ip) {
		String[] split = ip.split("\\.");
		int one = Integer.parseInt(split[0])*256*256*256;
		int two = Integer.parseInt(split[1])*256*256;
		int three = Integer.parseInt(split[2])*256;
		int four = Integer.parseInt(split[3]);
		long decimalIP = one+two+three+four;
		return decimalIP;
	}

}
