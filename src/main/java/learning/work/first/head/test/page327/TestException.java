package learning.work.first.head.test.page327;

/**
 * Created by qiong.liu on 2018/10/26.
 * //当异常发生时,会跳到catch代码块,若非try catch 捕获,则会跳出当前的程序执行
 * 当可能会捕获到多个异常时,可使用多个catch块对异常进行分别处理,且异常处理有顺序,按照先小后大处理
 */
public class TestException {
    public static void main(String[] args) {
        String test="yes";
        try {
            System.out.println("start try");
            doRisky(test);
            System.out.println("end try");
        }catch (ScaryException se){
            System.out.println("scary Exception");
        }
        finally {
            System.out.println("finally");
        }
        System.out.println("end of main");

    }

    public static void doRisky(String test) throws ScaryException{
        System.out.println("start risky");
        if ("yes".equals(test)){
            throw new ScaryException();
        }
        System.out.println("end risky");
        return;
    }
}
