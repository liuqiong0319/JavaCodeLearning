package java.learning.self.dateDemo;

import java.util.Calendar;

/**
 * Created by qiong.liu on 2018/4/13.
 */
public class DateParseDemo3 {
    //// 4,获取给定年份的2月有多少天？【面试题】

    public static void main(String[] args) {
        int year=2020;
        int days=getFebDays(year);
        System.out.println(days);
    }

    private static int getFebDays(int year) {
        Calendar c=Calendar.getInstance();
        c.set(year,2,1);//表示某一年的3月1日,通过相减,得到该年份2月的最后一天.
        c.add(c.DAY_OF_MONTH,-1);
        int day=c.get(Calendar.DAY_OF_MONTH);
        return day;

    }
}
