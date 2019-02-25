package java.learning.self.socketService.tcpDemo2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by qiong.liu on 2018/5/30.
 */
public class TCPServerDemo2 {
    public static void main(String[] args) throws IOException {
        ServerSocket ss=new ServerSocket(10005);
        while (true){
            Socket s=ss.accept();
            System.out.println(s.getInetAddress().getHostAddress()+"进来了");

            // 读取客户端的发送过来的数据
            InputStream in=s.getInputStream();
            byte[] buf=new byte[1024];
            int length=in.read(buf);
            String text=new String(buf,0,length);
            System.out.println(text);

            //给客户端回信
            OutputStream os=s.getOutputStream();
            os.write("客户端,我已收到".getBytes());

            s.close();
        }
//      关闭服务端。如果不断的获取客户端，不用关闭服务端。
//		ss.close();
    }

}
