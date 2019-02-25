package java.learning.self.regular;

import java.util.Scanner;

/**
 * Created by qiong.liu on 2018/9/3.
 */
public class QQCheckDemo {

    /**
     * 需求:对qq号进行校验
     * 1,必须为纯数字
     * 2,长度在5~15之间
     * 方法:使用String.length()
     * 3,不能以0开头
     * 方法:使用String.startsWith()
     * 1,必须为纯数字
     *  方法1,使用String.charAt获取qq号的每个字符;使用Character.isDigit判断是否为数字
     *  方法2,使用Long.parseLong(String s) 解析,捕获异常信息
     *  方法3,使用正则表达式String.matcher()
     *
     *
     * \d  [0-9]
     * \D  [^0-9]
     * \w  单次字符[a-zA-Z0-9]
     * ^   行开头
     * $   行结尾
     * \b  单词边界
     *   X?	X，一次或一次也没有
         X*	X，零次或多次
         X+	X，一次或多次
         X{n}	X，恰好 n 次
         X{n,}	X，至少 n 次
         X{n,m}	X，至少 n 次，但是不超过 m 次
     */


    public static void main(String[] args) {

        String qq;
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入qq号: ");
        qq = scanner.nextLine();


//        boolean result = check(qq);
//        boolean result=check_integer(qq);
        boolean result=check_regular(qq);

        if (result == false) {
            System.out.println("qq号输入不符合规范,请重新输入!");
        } else
            System.out.println("输入正确!");
    }

    public static boolean check_regular(String qq) {
//        return qq.matches("[1-9][0-9]{4,14}");//正则表达式,字符长度使用{},范围之间使用逗号隔开,{4,14}表示长度是4~14.
        return qq.matches("[^0]\\d{4,14}");
    }


    public static boolean check_integer(String qq) {
        if(qq.isEmpty()){
            System.out.println("qq号不能为空");
            return false;
        }
        if(qq.startsWith("0")){
            System.out.println("qq号不能以0开头");
            return false;
        }
        if (qq.length()<5 || qq.length()>15){
            System.out.println("qq号长度需要5~15之间");
            return false;
        }
        try{
        Long.parseLong(qq);}
        catch (NumberFormatException e){
            System.out.println("qq必须为纯数字");
            return false;
        }
        return true;
    }

    public static boolean check(String qq) {
        if (!qq.isEmpty()) {
            if (!qq.startsWith("0")) {
                if (qq.length() >= 5 && qq.length() <= 15) {
                    for (int i = 0; i < qq.length(); i++) {
                        if (!Character.isDigit(qq.charAt(i))) {
                            return false;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

}