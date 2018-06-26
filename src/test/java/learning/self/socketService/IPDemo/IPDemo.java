package learning.self.socketService.ipDemo;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by qiong.liu on 2018/5/29.
 */
public class IPDemo {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress ia=InetAddress.getLocalHost();

        InetAddress ia2=InetAddress.getByName("www.sina.com.cn");
        System.out.println(ia2.getHostAddress()+"……"+ia2.getHostName());
    }
}
