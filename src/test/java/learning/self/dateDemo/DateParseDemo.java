package learning.self.dateDemo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by qiong.liu on 2018/4/12.
 */
public class DateParseDemo {
    //将给定的字符串转换成日期对象,即解析
    public static void main(String[] args) throws ParseException {
//        String str_date="2012年3月17日";
//        DateFormat dateFormat=DateFormat.getDateInstance(DateFormat.LONG);
//        Date date=dateFormat.parse(str_date);
//        System.out.println(date);


        String str_date = "2012年3月17日";



        //日期格式器。DateFormat
        DateFormat dateFormat =new SimpleDateFormat("yyyy年MM月dd日");

        Date date = dateFormat.parse(str_date);

        System.out.println(date);
    }
}
