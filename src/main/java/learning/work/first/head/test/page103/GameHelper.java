package learning.work.first.head.test.page103;

import java.util.Scanner;

/**
 * Created by qiong.liu on 2018/10/8.
 * //获取用户的猜测数字的输入,使用scanner方法
 */
public class GameHelper {
    public int getUserInput() {
        System.out.println("请输入您猜测的数字:");
        Scanner scanner=new Scanner(System.in);
        int userGuess=scanner.nextInt();
       return  userGuess;
    }
}

