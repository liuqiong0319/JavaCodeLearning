package learning.self.socketService.udpDemo2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by qiong.liu on 2018/5/29.
 */
public class ReceiveMessageDemo2 {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds=new DatagramSocket(10001);

        while (true) {
            byte[] buff = new byte[1024];
            DatagramPacket dp = new DatagramPacket(buff, buff.length);
            ds.receive(dp);

            String ip = dp.getAddress().getHostAddress();
            int port = dp.getPort();
            String text = new String(dp.getData(), 0, dp.getLength());
            System.out.println(ip + "……" + port + "……" + text);

        }
//        ds.close();

    }
}
