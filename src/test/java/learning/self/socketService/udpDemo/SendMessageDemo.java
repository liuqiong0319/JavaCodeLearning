package learning.self.socketService.udpDemo;

import java.io.IOException;
import java.net.*;

/**
 * Created by qiong.liu on 2018/5/29.
 */
public class SendMessageDemo {

    /**
     * 通过UDP发送广播
     * 1,确定发送报文的对象DatagramSocket
     * 2,将需要发送的报文进行打包
     * 3,进行发送
     * 4,关闭发送
     * @param args
     */
    public static void main(String[] args) throws IOException {

//        1,确定发送报文的对象DatagramSocket
        DatagramSocket ds=new DatagramSocket();
//        2,将需要发送的报文进行打包成DatagramPacket
        String message="UDP send message run...";
        DatagramPacket dp=new DatagramPacket(message.getBytes(),message.length(), InetAddress.getLocalHost(),10000);
//        进行发送,使用send方法
        ds.send(dp);
//        关闭发送
        ds.close();

    }
}
