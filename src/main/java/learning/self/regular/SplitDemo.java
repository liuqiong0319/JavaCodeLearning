package java.learning.self.regular;

/**
 * Created by qiong.liu on 2018/9/3.
 */
public class SplitDemo {
    //切割
    public static void main(String[] args) {
         split();
    }

    private static void split() {
//        String str="zhangsan lisi wangwu";//重复出现的字符作为分隔符
//        String regex=" ";


//        String str="zhangsan       lisi    wangwu       ";//重复出现的字符作为分隔符
//        String regex=" +";

//        String str="zhangsan.lisi.wangwu";
//        String regex="\\.";


        //组的概念,使用()封装,直接通过编号可以调用对应的组,调用方式直接写已后组的编号前面加上\\,如(.)\1表示使用已有组的第一组内容
        //((A)(B(c)))  有几个括号就有几组,左括号从左到右组编号逐渐增加.
//        1    	(A)(B(C)))
//        2    	\A
//        3    	(B(C))
//        4    	(C)
//        String str="wera###bc23..4a222ad#c#";//重复出现的字符作为分隔符
//        String regex="(.)\\1+";

        String str="werabc###bc23..4a222ad#c#";//重复出现的字符作为分隔符
        String regex="((a)(b(c)))\\1+";
        String[] strs=str.split(regex);
        for(String s:strs){
            System.out.println("-"+s+"-");
        }
    }
}
