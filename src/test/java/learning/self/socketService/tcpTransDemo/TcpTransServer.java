package learning.self.socketService.tcpTransDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by qiong.liu on 2018/6/7.
 */
public class TcpTransServer {
    public static void main(String[] args)  throws IOException{
        System.out.println("服务端运行了...");
        //1,创建服务端,监听端口
        ServerSocket ss=new ServerSocket(10006);

        while (true) {
            Socket s = ss.accept();

            System.out.println(s.getInetAddress().getHostAddress()+"....connected");
            //2,源:socket 输入流 ,读取客户端发回来的数据
            BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));

            //目的:socket输出流,将读取到的数据进行转换,再发送给客户端
            PrintWriter out = new PrintWriter(s.getOutputStream(),true);


            //频繁的读写操作
            String line = null;
            while ((line = bufIn.readLine()) != null) {
                if ("over".equals(line)) {//如果客户端发过来的是over,转换结束
                    break;
                }
                System.out.println(line);

                out.println(line.toUpperCase());
            }


            //关闭客户端
            s.close();
        }
    }
}
