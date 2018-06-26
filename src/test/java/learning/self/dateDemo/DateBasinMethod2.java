package learning.self.dateDemo;

import java.util.Calendar;

/**
 * Created by qiong.liu on 2018/4/12.
 */
public class DateBasinMethod2 {
    //如何获取字符串中指定的日期信息呢？比如获取年，并判断。
//    Calendar 类是一个抽象类，它为特定瞬间与一组诸如 YEAR、MONTH、DAY_OF_MONTH、HOUR 等
// 日历字段之间的转换提供了一些方法，并为操作日历字段（例如获得下星期的日期）提供了一些方法

    public static void main(String[] args) {
        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH)+1;
        int day=cal.get(Calendar.DAY_OF_MONTH);
        String week=getCnWeek(cal.get(Calendar.DAY_OF_WEEK));
        System.out.println(year+"年"+month+"月"+day+"日"+week);
    }

    private static String getCnWeek(int dayOfWeek) {
        if(dayOfWeek<0||dayOfWeek>7){
            throw new RuntimeException("没有对应的星期");
        }
        String[] week={"","星期日","星期一","星期二","星期三","星期四","星期五","星期六",};
        return week[dayOfWeek];
    }


}
