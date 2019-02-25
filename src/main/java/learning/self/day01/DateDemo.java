package java.learning.self.day01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by joan.liu on 2017/8/30.
 */
public class DateDemo {

	public static void dateInfo() throws ParseException {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss"); // mm：分钟；MM:月份；hh:12小时制；HH:24小时制；dd:日；DD:一年中的天数
		String today = sdf.format(d);
		System.out.println(d);
		System.out.println(today);
		String day = "2014-6-1 21:05:36";
		d = sdf.parse(day);
		System.out.println(d);

	}

	public static void main(String[] args) throws ParseException {
		dateInfo();
	}

}
