package java.learning.self.stringDemo;

/**
 * Created by qiong.liu on 2018/3/21.
 */


/**
 * 指定字符串,获取按照长度递减后的所有字符串
 *  String sb="helloWorld"
 *  int length=sb.length  时 str.substring(0,length)  返回 helloWorld
 *  length=sb.length-1时 str.substring(0,length-1),str.substring(1,length)  返回 helloWorl elloWorld
 *  length=sb.length-2时 str.substring(0,length-2),str.substring(1,length-1),str.substring(2,length) 返回 helloWor  elloWorl  lloWorld
 *  ……
 *
 *
 */
public class StringDescend {
    public static void main(String[] args) {
        String str="helloWorld";
        stringDescend(str);

    }

    private static void stringDescend(String str) {
        for (int i = 0; i <str.length() ; i++) {
            for (int j = 0,k=str.length()-i; k <=str.length() ; j++,k++) {
               String  temp= str.substring(j,k);
                System.out.println(temp);

            }

        }
    }
}
