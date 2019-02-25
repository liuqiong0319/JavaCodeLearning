package java.learning.self.socketService.tcpTransDemo;

import java.io.*;
import java.net.Socket;

/**
 * Created by qiong.liu on 2018/6/7.
 */
public class TcpTransClient {
    //需求:将用户发送给服务端的数据进行转换后返回给客户端

    public static void main(String[] args) throws IOException {

        System.out.println("客户端运行了...");
        //1,创建客户端,明确地址和端口
        Socket s=new Socket("10.12.36.137",10006);

        //明确源和目的 源:键盘录入;
        BufferedReader bufr=new BufferedReader(new InputStreamReader(System.in));

        //因为读入的是字符流,而在服务端显示的也是字符流,故使用字符流较好,再加上高效,使用bufferedWriter
//        BufferedWriter bufOut=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
//      打印流,既能接收字节流,也能接收字符流
//        目的:网络,socket输出流,将客户端的键盘录入发送给服务端
        PrintWriter pwOut=new PrintWriter(s.getOutputStream(),true);
//        OutputStream osOut=s.getOutputStream();
        //源:socket 读取流,读取服务端发回来的大写数据,
//        InputStream isIn=s.getInputStream();
        BufferedReader bufIn=new BufferedReader(new InputStreamReader(s.getInputStream()));

//        目的:客户端显示器,将转换后的大写数据显示出来

        //频繁的读写操作
        String line=null;
        while((line=bufr.readLine())!=null){
            if("over".equals(line)){
                break;
            }
            //将读取键盘的数据发送给服务端
            pwOut.println(line);

//          读取服务端返回的数据
            String upperText=bufIn.readLine();
            System.out.println(upperText);


        }

        //关闭资源

        s.close();
    }
}
