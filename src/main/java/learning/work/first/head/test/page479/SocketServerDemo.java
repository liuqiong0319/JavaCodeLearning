package learning.work.first.head.test.page479;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by qiong.liu on 2018/11/21.
 */
public class SocketServerDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket=new ServerSocket(10006);

        Socket ss=serverSocket.accept();

        BufferedReader bufIn = new BufferedReader(new InputStreamReader(ss.getInputStream()));
        PrintWriter out = new PrintWriter(ss.getOutputStream(),true);

        String line = null;
        while ((line = bufIn.readLine()) != null) {
            if ("over".equals(line)) {//如果客户端发过来的是over,转换结束
                break;
            }
            System.out.println(line);

            out.println(line.toUpperCase());
        }
    }
}
