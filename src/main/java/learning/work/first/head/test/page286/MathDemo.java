package learning.work.first.head.test.page286;

/**
 * Created by qiong.liu on 2018/10/25.
 */
public class MathDemo {
    public static void main(String[] args) {
        int randomNum=(int)(Math.random()*4);
        System.out.println(randomNum);//返回0.0-1.0之间的双精度浮点数

        int absNum=Math.abs(-123);
        System.out.println(absNum);//返回双精度浮点数型参数的绝对值.改方法有覆盖版本,故传入整型会返回整型,传入浮点数会返回浮点数

        int roundNum=Math.round(-23.54f);//根据参数是浮点型或双精度浮点型返回四舍五入之后的整型或长整型值.
        System.out.println(roundNum);//f表示浮点数,返回int型;

        long roundNum2=Math.round(25.19);
        System.out.println(roundNum2);//不加f表示双精度浮点数,返回long型

        int minNum=Math.min(45,39);//返回量参数中较小的那个,对应的类型有覆盖版本
        System.out.println(minNum);

        double maxNum=Math.max(23.53,53.12);//返回量参数中较大的那个,对应的类型有覆盖版本
        System.out.println(maxNum);


    }
}
