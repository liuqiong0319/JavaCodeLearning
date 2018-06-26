package learning.self.socketService.tcpUploadText;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by qiong.liu on 2018/6/8.
 */
public class UploadTextServer {
    public static void main(String[] args) throws IOException {
        //1,创建服务端
        ServerSocket ss=new ServerSocket(10008);
        while (true){
            Socket s=ss.accept();
            System.out.println(s.getInetAddress().getHostAddress()+"......connected");

            //源:socket输入流,接收客户端发过来的数据
            BufferedReader buIn=new BufferedReader(new InputStreamReader(s.getInputStream()));

            //目的:文件
            PrintWriter pw=new PrintWriter(new FileWriter("/Users/qiong.liu/Documents/UploadSql.csv"),true);

            //频繁的读写操作
            String line=null;
            while((line=buIn.readLine())!=null){
                pw.println(line);
                //定义结束标志
//                if(line.equals("over")){
//                    break;
//                }
            }

            //返回客户端上传成功字样
            PrintWriter out=new PrintWriter(s.getOutputStream(),true);
            out.println("上传成功");
            s.close();
        }
    }
}
