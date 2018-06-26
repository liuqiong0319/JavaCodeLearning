package learning.self.socketService.udpDemo2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by qiong.liu on 2018/5/29.
 */
public class SendMessageDemo2 {

    /**
     * 通过UDP发送广播
     * 1,确定发送报文的对象DatagramSocket
     * 2,将需要发送的报文进行打包,即时通讯
     * 3,进行发送
     * 4,关闭发送
     * @param args
     */
    public static void main(String[] args) throws IOException {

        System.out.println("UDP send message run...");
//        1,确定发送报文的对象DatagramSocket
        DatagramSocket ds=new DatagramSocket();
//        2,将需要发送的报文进行打包成DatagramPacket
        BufferedReader bufr=new BufferedReader(new InputStreamReader(System.in));
        String line=null;
        while ((line=bufr.readLine())!=null){
            if ("over".equals(line)){
                break;
            }

            byte[] buf=line.getBytes();
            DatagramPacket dp=new DatagramPacket(buf,buf.length, InetAddress.getByName("10.12.36.137"),10001);
            //        进行发送,使用send方法
            ds.send(dp);
        }



//        关闭发送
        ds.close();

    }
}
