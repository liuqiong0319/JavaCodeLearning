package learning.work.first.head.test.page139;

import java.util.Scanner;

/**
 * Created by qiong.liu on 2018/10/8.
 */
public class GuessInput {
    public int getGuessNumber(){
        System.out.print("请输入您猜测的数字:");
        Scanner scanner=new Scanner(System.in);
        int guessNumber=scanner.nextInt();
        return guessNumber;
    }
}
