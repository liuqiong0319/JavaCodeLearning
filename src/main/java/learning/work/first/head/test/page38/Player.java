package learning.work.first.head.test.page38;

/**
 * Created by qiong.liu on 2018/9/26.
 */
public class Player {
    int guessNumber;
    public  void guess(){
        guessNumber=(int) (Math.random()*10);
        System.out.println("I'm guessing:"+guessNumber);
    }
}
