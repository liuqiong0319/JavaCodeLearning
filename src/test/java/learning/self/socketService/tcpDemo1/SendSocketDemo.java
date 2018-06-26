package learning.self.socketService.tcpDemo1;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by qiong.liu on 2018/5/30.
 */
public class SendSocketDemo {

    //tcp协议客户端使用Socket
    public static void main(String[] args) throws IOException {
        Socket s=new Socket("10.12.36.137",10004);

        OutputStream os=s.getOutputStream();

        os.write("tcp的client端来了".getBytes());

        s.close();
    }
}
