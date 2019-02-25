package java.learning.self.socketService.tcpDemo2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by qiong.liu on 2018/5/30.
 */
public class TCPClientDemo2 {

    /**
     * 案例二:实现tcp协议的收发机制
     * 客户端
     */
    public static void main(String[] args) throws IOException{
        Socket s=new Socket("10.12.36.137",10005);

        OutputStream os=s.getOutputStream();
        os.write("服务端,我来了".getBytes());

        //读取服务端返回的数据
        InputStream is=s.getInputStream();
        byte[] buf=new byte[1024];
        int len=is.read(buf);
        String text=new String (buf,0,len);
        System.out.println(text);
        s.close();
    }

}
