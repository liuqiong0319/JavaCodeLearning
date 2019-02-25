package java.learning.self.systemDemo;

import java.util.Properties;

/**
 * Created by qiong.liu on 2018/4/9.
 */
public class BasicSystemMethod {
    private static final String  LINE_SEPARATOR=System.getProperty("line.separator");

    public static void main(String[] args) {
        long time=System.currentTimeMillis();
        //应用:获取程序运行的时间
        System.out.println(time);

        Properties pro= System.getProperties();



//        Set<String> setPros=pro.stringPropertyNames();
//        for(String key:setPros){
//            String value=pro.getProperty(key);
//            System.out.println(key+">>>"+value);
//        }

        String osName=pro.getProperty("os.name");
        System.out.println(osName);

        //换行 line.separator	行分隔符（在 UNIX 系统中是“/n”）系统跨平台型强
        System.out.println("hello"+LINE_SEPARATOR+"world");
    }
}
