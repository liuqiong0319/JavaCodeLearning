package learning.work.first.head.test.page305;

import java.util.Calendar;

/**
 * Created by qiong.liu on 2018/10/26.
 */
public class CalendarDemo {
    public static void main(String[] args) {
        Calendar calendar1=Calendar.getInstance();
        calendar1.set(2018,10,26,12,34);
        System.out.println(calendar1.getTime());
        long day1=calendar1.getTimeInMillis();
        System.out.println(day1);
        calendar1.add(calendar1.DAY_OF_MONTH,8);
        //DAY_OF_MONTH和DATE都表示日期
        //HOUR/HOUR_OF_DAY都表示小时
        System.out.println(calendar1.getTime());
        calendar1.roll(calendar1.DATE,20);//滚动天数,只有日期字段会动,月份不会动,即若超过30天,日期累加至新有效日期,月份不进位
        System.out.println(calendar1.getTime());
        calendar1.set(calendar1.DATE,1);
        System.out.println(calendar1.getTime());

    }
}
