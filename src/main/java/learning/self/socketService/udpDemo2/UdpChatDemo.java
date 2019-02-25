package java.learning.self.socketService.udpDemo2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by qiong.liu on 2018/5/30.
 */
public class UdpChatDemo {
    //实现多人聊天,即收发均在同时发生

    /**
     * 思路:
     * 使用到多线程和UDP的接收发送
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        DatagramSocket sendSocket=new DatagramSocket();
        DatagramSocket recSocket=new DatagramSocket(10003);

        Send send=new Send(sendSocket);
        Receive rec=new Receive(recSocket);

        Thread t1=new Thread(send);
        Thread t2=new Thread(rec);

        t1.start();
        t2.start();
    }
}

class Send implements Runnable{

    private DatagramSocket ds;

    public Send(DatagramSocket ds) {
        this.ds = ds;
    }

    @Override
    public void run() {
        try {
            BufferedReader bufr=new BufferedReader(new InputStreamReader(System.in));
            String line=null;
            while ((line=bufr.readLine())!=null){
                byte[] buf=line.getBytes();
                DatagramPacket dp=new DatagramPacket(buf,buf.length, InetAddress.getByName("10.12.36.137"),10003);
                ds.send(dp);
                if ("886".equals(line)) {
                    break;
                }
            }
            ds.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

class Receive implements Runnable{

   private DatagramSocket ds;

    public Receive(DatagramSocket ds) {
        this.ds = ds;
    }

    @Override
    public void run() {
        while (true){
            try {
                byte[] buf=new byte[1024];
                DatagramPacket dp=new DatagramPacket(buf,buf.length);
                ds.receive(dp);
                String  ip=dp.getAddress().getHostAddress();
                int port=dp.getPort();
                String text=new String(dp.getData(),0,dp.getLength());
                System.out.println(ip + ":" + port + ":" + text);
                if(text.equals("886")){
                    System.out.println(ip+"...离开聊天室了");
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }


    }
}
