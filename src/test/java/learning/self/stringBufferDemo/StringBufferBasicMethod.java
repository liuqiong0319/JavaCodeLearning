package learning.self.stringBufferDemo;

/**
 * Created by qiong.liu on 2018/3/21.
 * StringBuffer 字符串缓冲区,长度可变
 */
public class StringBufferBasicMethod {
    public static void main(String[] args) {
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append(123).append(true).append('c');
        System.out.println(stringBuffer.toString());
        stringBuffer.insert(2,"hello");
        System.out.println(stringBuffer);
        System.out.println(stringBuffer.substring(2,7));
    }
}
