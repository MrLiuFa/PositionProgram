package day06.selfcode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TestSplit {

	public static void main(String[] args) {
		//SimpleDateFormat sdf = new SimpleDateFormat("%td/%tb/%tY:%tH:%tM:%tS");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss",Locale.US);
		String s = "194.237.142.21 - - [18/Sep/2013:06:49:18 +0000] \"GET /wp-content/uploads/2013/07/rstudio-git3.png HTTP/1.1\" 304 0 \"-\" \"Mozilla/4.0 (compatible;)\"";
		
		String[] split = s.split(" ");
		String time = split[3].substring(1, split[3].length());
		System.out.println(time);
		try {
			Date parse = sdf.parse(time);
			System.out.println(parse);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

}
