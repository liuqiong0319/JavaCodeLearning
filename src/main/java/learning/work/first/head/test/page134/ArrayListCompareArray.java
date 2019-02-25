package learning.work.first.head.test.page134;

import java.util.ArrayList;

/**
 * Created by qiong.liu on 2018/10/9.
 */
public class ArrayListCompareArray {

    public static void main(String[] args) {
        arrayList();
        array();
    }

    public static  void arrayList(){
        System.out.println("-----arrayList----");
        ArrayList<String>  mylist=new ArrayList<String>();
        String a=new String("whoooo");
        mylist.add(a);
        String b=new String("Frog");
        mylist.add(b);
        System.out.println(mylist.toString());
        int size=mylist.size();
        System.out.println("长度为:"+size);
        Object o=mylist.get(1);
        System.out.println(o.toString());
        mylist.remove(1);
        System.out.println(mylist.toString());
        boolean isIn=mylist.contains(b);
        System.out.println(isIn);
    }


    public  static  void array(){
        System.out.println("-----array----");
        String[] mylist=new String[2];
        String a=new String("whoooo");
        mylist[0]=a;
        String b=new String("Frog");
        mylist[1]=b;
        int size=mylist.length;
        for (String list:mylist){
            System.out.println(list);
        }
        System.out.println("长度为:"+size);
        String o=mylist[1];
        System.out.println(o);
        mylist[1]=null;
        for (String list:mylist){
            System.out.println(list);
       }

        boolean isIn=false;
        for (String list:mylist) {
            if (b.equals(list)){
                isIn=true;
            }
        }

        System.out.println(isIn);


    }
}
