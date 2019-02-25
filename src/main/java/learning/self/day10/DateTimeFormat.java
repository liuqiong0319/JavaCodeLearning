package java.learning.self.day10;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * DateFormat 是日期/时间格式化子类的抽象类
 *SimpleDateFormat是DateFormat的子类,输入使用format方法进行时间转换
 *  Created by qiong.liu on 2018/1/12.
 */
public class DateTimeFormat {
    public static void main(String[] args) {
        //获取该国家/地区的标准日期格式
        Date date=new Date();
        String myDate=DateFormat.getDateInstance().format(date);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String myDate2=simpleDateFormat.format(date);
        System.out.println("myDate:"+myDate+"\n"+"myDate2:"+myDate2);

        Calendar c= Calendar.getInstance();
        c.add(Calendar.MONTH,-1);
        String beginDate=simpleDateFormat.format(c.getTime());
        String endDate=simpleDateFormat.format(date);
        System.out.println("beginDate:"+beginDate+"\n"+"endDate:"+endDate);
    }
}
