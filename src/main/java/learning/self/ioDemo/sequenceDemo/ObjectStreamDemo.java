package java.learning.self.ioDemo.sequenceDemo;

import java.learning.self.ioDemo.domain.Person;

import java.io.*;

/**
 * Created by qiong.liu on 2018/5/22.
 */
public class ObjectStreamDemo {
    public static void main(String[] args) throws IOException,ClassNotFoundException{

        
//        writeObject();
        readObject();


        
    }

    private static void readObject() throws IOException,ClassNotFoundException{


        FileInputStream fis=new FileInputStream("/Users/qiong.liu/Downloads/obj.object");
        ObjectInputStream ois=new ObjectInputStream(fis);
        Object obj= ois.readObject();
        System.out.println(obj.toString());

    }

    private static void writeObject() throws IOException {
        Person person=new Person("zhangsan",23);
        FileOutputStream fos=new FileOutputStream("/Users/qiong.liu/Downloads/obj.object");
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        oos.writeObject(person);
        oos.close();
    }
}
