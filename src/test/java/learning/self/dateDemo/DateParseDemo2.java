package learning.self.dateDemo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by qiong.liu on 2018/4/13.
 */
public class DateParseDemo2 {
//    练习3,"2013-4-25"到"2013年7月29日"到底有多少天？

    public static void main(String[] args)throws ParseException  {
        String date1="2013-4-25";
        String date2="2013年4月26日";
        String dateFormat1="yyyy-MM-dd";
        String dateFormat2="yyyy年MM月dd日";

        int diffDate=getDiffDate(date1,date2,dateFormat1,dateFormat2);
        System.out.println(diffDate);

    }

    private static int getDiffDate(String date1, String date2,String parseFormat1,String parseFormat2) throws ParseException {
//        指定输入字符串的格式
        DateFormat dateFormat1= new SimpleDateFormat(parseFormat1);
        DateFormat dateFormat2= new SimpleDateFormat(parseFormat2);
//        使用解析语句将对应字符串解析成相应格式的日期
        Date date1_1=dateFormat1.parse(date1);
        Date date1_2=dateFormat2.parse(date2);
//      将日期转换成毫秒数
        long time1=date1_1.getTime();
        long time2=date1_2.getTime();
//      相减
        long diff=Math.abs(time1-time2);
//      转换成对应天数
        int diffDay=(int)(diff/1000/60/60/24);
        return diffDay;

    }


}
