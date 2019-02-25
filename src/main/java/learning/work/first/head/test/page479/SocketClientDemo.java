package learning.work.first.head.test.page479;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by qiong.liu on 2018/11/21.
 */
public class SocketClientDemo {
    public static void main(String[] args) throws IOException {
        Socket clientSocket=new Socket("10.12.36.137",10006);

        PrintWriter writer=new PrintWriter(clientSocket.getOutputStream());



        writer.println("send message");
    }
}
