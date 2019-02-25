package java.learning.self.reflect;

import java.learning.self.reflect.daemon.NoteBook;
import java.learning.self.reflect.daemon.USB;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

/**
 * Created by qiong.liu on 2018/8/30.
 */
public class ReflectDemo {

    /**
     * 需求:笔记本电脑notebook,需要添加外围设备如鼠标,键盘灯,且外围设备不确定
     *
     * 反射技术,要将需要扩展的外围设备配置到配置文件中
     */

    public static void main(String[] args) throws Exception{
        NoteBook noteBook=new NoteBook();
        noteBook.run();

        File file =new File("/Users/qiong.liu/IdeaProjects/JavaCodeLearning/src/config/usb.properties");

        FileReader fr=new FileReader(file);

        Properties pro=new Properties();
        pro.load(fr);

        for (int i=1;i<=pro.size();i++){
            String usbName=pro.getProperty("USBName"+i);
            Class usbClass=Class.forName(usbName);

            Object obj=usbClass.newInstance() ;
            noteBook.runUSB((USB)obj);

        }

        fr.close();

    }
}
