package java.learning.self.stringDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiong.liu on 2018/3/19.
 */
public class StringBasicMethod {

    public static void main(String[] args) {
        String s1="abc";
        String s2="abc";

        System.out.println(s1==s2);
        System.out.println(s1.equals(s2));


        System.out.println("------比较字符串------->");
        String s3=new String ("abc");
        System.out.println("s3="+s3);
        System.out.println(s1==s3);
        System.out.println(s1.equals(s3));


        System.out.println("-----字符串长度----->");
        String s4="some hello world,I hate some one or something or someworld or somebody";
        System.out.println(s4.length());


        System.out.println("----获取字符位置---------");
        System.out.println(s4.indexOf('o'));


        System.out.println("----获取子字符串位置---------");
        System.out.println(s4.indexOf("some"));

        System.out.println("----获取给定位置的字符---------");
        System.out.println(s4.charAt(19));


        System.out.println("------获取字符的所有位置---------");

        List result=count(s4,'o');
        System.out.println(result.toString());

        System.out.println("------获取字符串的所有位置---------");
        List resultA=CountStr(s4,"some");
        System.out.println(resultA.toString());
    }

    public  static List count(String str, char c){
        List count=new ArrayList();
        for(int i=0;i<str.length();i++){
            char temp=str.charAt(i);
            if(c==temp){
                count.add(i);
            }
        }
        return count;
    }

    public static List CountStr(String str,String key){
        int index=str.indexOf(key);
        List listA=new ArrayList();
        if (index==-1)
            System.out.println("无此字符串");
        else {
            listA.add(index);
            while (str.indexOf(key, index + key.length()) != -1) {
                index = str.indexOf(key,index + key.length());
                listA.add(index);
            }
        }
        return listA;
    }
}
