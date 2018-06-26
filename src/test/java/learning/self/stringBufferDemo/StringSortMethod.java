package learning.self.stringBufferDemo;

import java.util.Arrays;

/**
 * Created by qiong.liu on 2018/3/21.
 * 对字符串中的字符进行自然排序
 * String()           初始化一个新创建的 String 对象，使其表示一个空字符序列。
   String(byte[] bytes)   通过使用平台的默认字符集解码指定的 byte 数组，构造一个新的 String。
 */
public class StringSortMethod {

    public static void main(String[] args) {
        String strs="feafwae223facw";
        String result=stringSort(strs);
        System.out.println(result);
    }

    public static String stringSort(String str){
        char[] chs=str.toCharArray();//将字符串转换为字符数组
        Arrays.sort(chs);//对数组进行排序
        return new String(chs);
    }
}
