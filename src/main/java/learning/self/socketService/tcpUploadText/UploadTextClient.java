package java.learning.self.socketService.tcpUploadText;

import java.io.*;
import java.net.Socket;

/**
 * Created by qiong.liu on 2018/6/8.
 */
public class UploadTextClient {
    /**
     * 上传文本客户端
     * 需求:将文本文件上传至服务器,服务器收到文件后返回上传成功字样给客户端
     */

    public static void main(String[] args) throws IOException{

        //1,创建客户端
        Socket s=new Socket("10.12.36.137",10008);

        //明确源和目的;源:文本文件;
        BufferedReader bufr=new BufferedReader(new FileReader("/Users/qiong.liu/Documents/基础查询sql.csv"));

        //目的:socket服务端
        PrintWriter out = new PrintWriter(s.getOutputStream(),true);

        //频繁的读写操作,将读取到的文本文件一行行的发送给服务器
        String line=null;
        while ((line=bufr.readLine())!=null){
            out.println(line);
        }
        //自定义结束标记.上传完成后,向服务端发送了结束标记。可以让服务端结束读取的动作。
//        out.println("over");
        //服务器端标准的结束标志,可以让服务器结束读取动作
        s.shutdownOutput();
        //读取服务器发回来的上传成功信息
        BufferedReader bufIn=new BufferedReader(new InputStreamReader(s.getInputStream()));
        String info=bufIn.readLine();
        System.out.println(info);

        bufr.close();
        s.close();
    }
}
