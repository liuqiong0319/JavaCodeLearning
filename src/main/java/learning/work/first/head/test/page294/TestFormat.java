package learning.work.first.head.test.page294;

/**
 * Created by qiong.liu on 2018/10/25.
 */
public class TestFormat {
    public static void main(String[] args) {
        //format()方法,format(格式指令,要格式的值)
        String s=String.format("%,d",1000000000);//%表示把参数放在这里,代表一项变量,表示第二个参数以第一个参数所表示带有逗号的整数(decimal)方式表示
        System.out.println(s);

//        format()方法的第一个参数被称为格式化串,他可以带有实际上就是要这么输出而不用转义的字符.当看到%时,要把他想做是会被方法其余参数替换掉的位置.
        System.out.println(String.format("I have %,.2f bugs to fix.",334145.236));
//        %,d 代表以十进制整数带有逗号的方式来表示
//        %.2f 代表以小数点后两位的方式来格式化次浮点数
//        %,.3f 代表整数部分以有逗号的形式表示,小数部分以三位来格式化.

        //9表示最小的字符数,可以超过此宽度,若不足则会自动补足;3表示浮点数的精确度,注意前方有个.;f表示类型,一定要指定类型,且必须放在最后
        System.out.println(String.format("This is an float %,9.3f",41.31));

//        类型d(decimal),f(float),x(hexadecimal,16进制),c(character)
        System.out.println(String.format("%d",42));
        System.out.println(String.format("%.3f",42.123043));
        System.out.println(String.format("%x",42));
        System.out.println(String.format("%c",42));
//        多个参数
        System.out.println(String.format("one number is %,d,second number is %,.2f,third number is %x,last number is %c",42,42.000,42,42));

    }
}
