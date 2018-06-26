package learning.work;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by qiong.liu on 2018/5/31.
 */
public class WriterFile {
    public static void main(String[] args) throws IOException {
        File file=new File("/Users/qiong.liu/Documents/adhocPerformanceTest/betaPerformance/actionSid.txt");

        BufferedWriter bufw=new BufferedWriter(new FileWriter(file,true));

        bufw.write("2324");

        bufw.close();

        String result="{\"ver\":\"1.0\",\"soa\":{\"req\":\"unknown^^4432327084442091901|1515509355174\"},\"result\":\"{\\\"code\\\":200,\\\"data\\\":6409,\\\"message\\\":\\\"执行成功\\\"}\",\"ex\":null}";
//        String adhocSid = vars.get("result").replaceAll("(.*)\"data\":","")
//                .replaceAll("\"message\"(.*)","")
//                .replaceAll(",","")
//                .trim();
////
//        String actionSid=result.replaceAll("(.*)\"data\":","").replaceAll("\"message\"(.*)","").replaceAll(",","").trim();

//        Logger.getLogger(actionSid);


        if(!file.exists()){
            file.createNewFile();
        }

    }
}
