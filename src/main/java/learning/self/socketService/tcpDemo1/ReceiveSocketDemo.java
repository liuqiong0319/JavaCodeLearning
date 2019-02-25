package java.learning.self.socketService.tcpDemo1;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by qiong.liu on 2018/5/30.
 */
public class ReceiveSocketDemo {
    //tcp协议获取流信息,服务端,使用ServerSocket
    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(10004);
        while (true) {
            Socket s = ss.accept();

            String ip = s.getInetAddress().getHostAddress();

            System.out.println(ip + "用户连接进来了……");

            InputStream is = s.getInputStream();
            byte[] buf = new byte[1024];
            int length=is.read(buf);
            String text = new String(buf, 0, length);
            System.out.println(text);

            s.close();

        }
//        ss.close();
    }
}
