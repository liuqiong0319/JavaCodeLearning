package learning.work.first.head.test.page486;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by qiong.liu on 2018/11/23.
 */
public class SimpleChatServer {
    Socket s;

    public static void main(String[] args) throws Exception{
        SimpleChatServer simpleChatServer=new SimpleChatServer();
        simpleChatServer.go();
    }

    private  void go()throws Exception {
        ServerSocket ss=new ServerSocket(5001);
        s =ss.accept();
        BufferedReader bw=new BufferedReader(new InputStreamReader(s.getInputStream()));
        String line=null;
        while ((line = bw.readLine()) != null){
            System.out.println("用户"+s.getInetAddress().getHostName()+":"+line);
        }
    }
}
