package learning.self.ioDemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by qiong.liu on 2018/5/22.
 */
public class AppCountDemo {
    //定义功能记录程序运行次数,满足使用次数后,给出提示:试用次数已到,请注册;

    public static void main(String[] args) throws IOException {
        if(isStop()){
            System.out.println("试用次数已到,请注册");
            return;
        }
        runCode();
    }

    private static boolean isStop() throws IOException {

        //存储运行次数的属性文件
        File configFile=new File("/Users/qiong.liu/Downloads/app.properties");
        if(!configFile.exists()){
            configFile.createNewFile();
        }

        //prop获取文件的属性信息
        Properties prop=new Properties();

        FileInputStream fis=new FileInputStream(configFile);

        prop.load(fis);

        String value=prop.getProperty("count");
        int count=0;
        //如果获取到的value值存在,则判断对应的值与指定的次数进行比较
        if(value!=null){
            count=Integer.parseInt(value);
            if(count>=5){
                return true;
            }
        }

        count++;
        prop.setProperty("count",Integer.toString(count));

        //对获取到的配置文件信息进行更新
        FileOutputStream fos=new FileOutputStream(configFile);

        prop.store(fos,"app run count");

        fos.close();
        fis.close();

        return false;
    }

    private static void runCode() {
        System.out.println("程序正在运行中...");
    }

}
