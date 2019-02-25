package java.learning.self.stringBufferDemo;

/**
 * Created by qiong.liu on 2018/3/21.
 */
public class StringTrimMethod {

    public static void main(String[] args) {
        String str="   hello world !    ";
        System.out.println("str="+str+"over");
        System.out.println("str="+str.trim());//trim返回字符串的副本，忽略前导空白和尾部空白
        System.out.println("str="+trimDemo(str));
    }


    public static String trimDemo(String str){
        int start=0;
        int end=str.length()-1;
        while(start<=end && str.charAt(start)<=' '){
            start++;
        }
        while(start<=end && str.charAt(end)<=' '){
            end--;
        }

        return str.substring(start,end+1);

    }
}
