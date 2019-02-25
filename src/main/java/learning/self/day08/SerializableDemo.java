package java.learning.self.day08;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by qiong.liu on 2017/12/18.
 * Java的"对象序列化"能让你将一个实现了Serializable接口的对象转换成byte流，这样日后要用这个对象时候，你就能把这些byte数据恢复出来，并据此重新构建那个对象了。
 * https://www.cnblogs.com/gw811/archive/2012/10/10/2718331.html
 * https://www.cnblogs.com/wangg-mail/p/4354709.html
 */
public class SerializableDemo {
    public  static void serializable() throws Exception {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("/Users/qiong.liu/Desktop/animal.dat"));
            Animal animal = new Animal();
            animal.setName("Dog");
            animal.setColor("Black");
            animal.setAlias(new String[]{"xiaoHei", "Gou", "GuaiGuai"});
            oos.writeObject(animal);
            oos.flush();
        } finally {
            if (null != oos) {
                oos.close();
            }
        }
    }
    public static void unSerializable() throws Exception {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("/Users/qiong.liu/Desktop/animal.dat"));
            Animal animal = (Animal) ois.readObject();
            System.out.println(animal.getName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != ois) {
                ois.close();
            }
        }
    }



    public static void main(String[] args) throws Exception {

        serializable();
        unSerializable();

    }
}
