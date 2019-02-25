package learning.work.first.head.test.page301;

import java.util.Date;

/**
 * Created by qiong.liu on 2018/10/25.
 */
public class DateFormat {
    public static void main(String[] args) {
//        日期转化使用%t
//        %tc表示完整的日期与时间
        System.out.println(String.format("%tc",new Date()));
//        %tr只有时间
        System.out.println(String.format("%tr",new Date()));
//        %tA %tB %tC 表示周\月\日,<表示格式化的时候重复利用之前用过的参数,注意%放在第一位.
        System.out.println(String.format("%tA,%<tB,%<tC",new Date()));
    }
}
