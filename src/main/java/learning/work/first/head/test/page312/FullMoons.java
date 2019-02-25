package learning.work.first.head.test.page312;

import java.util.Calendar;

/**
 * Created by qiong.liu on 2018/10/26.
 */
public class FullMoons {

    static final int DAY_IM=60*60*24*1000;
    public static void fullMoos(){
        Calendar calendar=Calendar.getInstance();
        calendar.set(2004,0,7,15,40);
        long day1=calendar.getTimeInMillis();
        for (int x=0;x<60;x++) {
            day1 += (DAY_IM * 29.52);
            calendar.setTimeInMillis(day1);
            System.out.println(String.format("the full moon on %tc", calendar));

        }



    }

    public static void main(String[] args) {
        fullMoos();
    }
}
