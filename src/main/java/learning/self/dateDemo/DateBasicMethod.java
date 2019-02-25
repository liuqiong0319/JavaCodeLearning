package java.learning.self.dateDemo;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by qiong.liu on 2018/4/12.
 * 从 JDK 1.1 开始，应该使用 Calendar 类实现日期和时间字段之间转换;
 * 使用 DateFormat 类来格式化和解析日期字符串。Date 中的相应方法已废弃。
 */
public class DateBasicMethod {

    public static void main(String[] args) {
        Date date =new Date(System.currentTimeMillis());//分配 Date 对象并初始化此对象
        System.out.println(date);
        // DateFormat.getDateInstance获取日期格式器
        DateFormat dateFormat=DateFormat.getDateInstance();//返回Apr 12, 2018
//        DateFormat dateFormat=DateFormat.getDateInstance(DateFormat.SHORT);//返回4/12/18
//        DateFormat dateFormat=DateFormat.getDateInstance(DateFormat.MEDIUM);//返回Apr 12, 2018(默认模式)
//        DateFormat dateFormat=DateFormat.getDateInstance(DateFormat.LONG);//返回April 12, 2018
//        DateFormat dateFormat=DateFormat.getDateInstance(DateFormat.FULL);//返回Thursday, April 12, 2018
        String date1=dateFormat.format(date);//format 将一个 Date 格式化为日期/时间字符串。
        System.out.println(date1);

        //getDateTimeInstance()获取日期/时间格式器
//        DateFormat timeFormat=DateFormat.getDateTimeInstance();//返回Apr 12, 2018 4:34:54 PM
//        getDateTimeInstance(int dateStyle, int timeStyle)获取日期/时间格式器
//        DateFormat timeFormat=DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT);//返回4/12/18 4:36 PM
//        DateFormat timeFormat=DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.MEDIUM);//返回4/12/18 4:38:50 PM
//        DateFormat timeFormat=DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.LONG);//返回4/12/18 4:41:03 PM CST
        DateFormat timeFormat=DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.FULL);//返回4/12/18 4:39:49 PM CST
        String time =timeFormat.format(date);
        System.out.println(time);

    }
}
