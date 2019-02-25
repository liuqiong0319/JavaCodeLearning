package java.learning.self.ioDemo.encodingDemo;

import java.io.UnsupportedEncodingException;

/**
 * Created by qiong.liu on 2018/5/10.
 */
public class CutStringDemo {

    //1，对字符串按照字节数截取(默认码表)，"abc你好" 有5个字符，有7个字节。
//    按照3个字节截取 abc ，按照四个字节截取 abc和你字的一半，如果出现中文一半舍弃。
//    请定义一个功能解决这个问题。


    public static void main(String[] args) throws UnsupportedEncodingException {

        String str = "abc你好cd谢谢";
//        int length=5;

        byte[] buf=str.getBytes("GBK");
        for(int i=0;i<buf.length;i++){
            String s=cutString(str,i+1);
            System.out.println("截取"+(i+1)+"个结果是:"+s);
        }

    }

    private static String cutString(String str,int length) throws UnsupportedEncodingException {

        //1,将字符串编码成字节数组

        byte[] buf=str.getBytes("GBK");

        int count=0;

        //2,对数组进行遍历,从截取位开始
        for(int i=buf.length-1;i>=0;i--){
            if(buf[i]<0){
                count++;
            }
            else {
                break;
            }
        }

        if(count%2==0){
            return new String(buf,0,length,"GBK");
        }
        else {
            return new String(buf,0,length-1,"GBK");
        }
    }
}
