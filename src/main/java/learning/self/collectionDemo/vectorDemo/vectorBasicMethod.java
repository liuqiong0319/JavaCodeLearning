package java.learning.self.collectionDemo.vectorDemo;

import java.util.Vector;

/**
 * Created by qiong.liu on 2018/3/28.
 */
public class vectorBasicMethod {
    public static void main(String[] args) {
        Vector vector=new Vector();
        vector.addElement("hello1");
        vector.addElement("hello2");
        vector.addElement("hello3");
        vector.addElement("hello4");
//        for(Enumeration en=vector.elements();en.hasMoreElements();){
//            System.out.println(en.nextElement());
//        }

        for(Object ob : vector){
            System.out.println(ob);
        }
    }


}
