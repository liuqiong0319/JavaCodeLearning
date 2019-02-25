package learning.work.first.head.test.page129;

import java.util.Scanner;

/**
 * Created by qiong.liu on 2018/10/9.
 */
public class UserGuess {
    public int guessNumber(){
        System.out.print("请输入猜测的坐标: ");
        Scanner scanner=new Scanner(System.in);
        int guessNumber=scanner.nextInt();
        return guessNumber;
    }
}
