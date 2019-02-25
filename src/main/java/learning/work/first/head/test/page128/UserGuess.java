package learning.work.first.head.test.page128;

import java.util.Scanner;

/**
 * Created by qiong.liu on 2018/10/9.
 *
 * 获取用户猜测的坐标
 */
public class UserGuess {

    public int guessInput(){
        System.out.print("请输入猜测的坐标: ");
        Scanner scanner=new Scanner(System.in);
        int userGuess=scanner.nextInt();
        return userGuess;
    }
}
