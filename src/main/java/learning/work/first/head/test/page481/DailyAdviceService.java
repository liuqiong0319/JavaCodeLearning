package learning.work.first.head.test.page481;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by qiong.liu on 2018/11/21.
 */
public class DailyAdviceService {

    String[] adviceList={"Take smaller bites","Go for the tight jeans.No they do Not make you look fat.","One word:inappropriate"};

    public void go() throws Exception{


        ServerSocket ss=new ServerSocket(5000);
        while (true) {
            Socket s = ss.accept();
            PrintWriter pw=new PrintWriter(s.getOutputStream());
            String advice=getAdvice();
            pw.println(advice);
            pw.close();
            System.out.println(advice);
        }
    }

    private String getAdvice() {
        int random=(int)(Math.random()*adviceList.length);
        return adviceList[random];
    }

    public static void main(String[] args) throws Exception{
        DailyAdviceService dailyAdviceService=new DailyAdviceService();
        dailyAdviceService.go();
    }
}
