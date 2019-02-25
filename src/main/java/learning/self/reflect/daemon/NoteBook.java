package java.learning.self.reflect.daemon;

/**
 * Created by qiong.liu on 2018/8/30.
 * 创建笔记本对象,并能支持外围设备,故需要添加接口
 */
public class NoteBook {
    public void run(){
        System.out.println("notebook run...");
    }
    public void runUSB(USB usb){
        if (usb!=null){
            usb.open();
            usb.close();
        }

    }
}



