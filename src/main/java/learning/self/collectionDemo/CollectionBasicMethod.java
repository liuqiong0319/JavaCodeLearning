package java.learning.self.collectionDemo;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by qiong.liu on 2018/3/22.
 */
public class CollectionBasicMethod {

    public static void collectionDemo(Collection coll,String str){
        //往集合中添加对象元素
       // 把String转int使用方法Integer.valueOf(str)
        Integer num1=Integer.valueOf(str);
        coll.add(num1);
        coll.add("hello");//添加对象
        System.out.println(coll);
        coll.remove(num1);//删除对象,返回值为boolean
        System.out.println(coll);
        coll.contains(num1);//包含对象,返回值为boolean
        System.out.println(coll.contains(num1));
        coll.remove(num1);
        System.out.println(coll);
    }

    public static void main(String[] args) {
        Collection collection=new ArrayList();
//getInteger取的是系统配置
        String str1="sun.arch.data.model";
        System.setProperty(str1,"1");
        Integer num2=Integer.getInteger(str1);
        System.out.println(num2);



        String str2="423423";
        collectionDemo(collection,str2);
        collection.remove(str2);

    }
}
