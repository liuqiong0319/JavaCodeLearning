package learning.work.first.head.test.page481;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by qiong.liu on 2018/11/21.
 */
public class DailyAdviceClient {
    public void go() throws Exception{
        Socket s=new Socket("127.0.0.1",5000);
        InputStreamReader is=new InputStreamReader(s.getInputStream());
        BufferedReader br=new BufferedReader(is);
        String advice=br.readLine();
        System.out.println(advice);
        s.close();
    }
    public static void main(String[] args) throws Exception{
        DailyAdviceClient dailyAdviceClient=new DailyAdviceClient();
        dailyAdviceClient.go();
    }
}
