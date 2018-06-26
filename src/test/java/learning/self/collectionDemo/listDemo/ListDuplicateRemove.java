package learning.self.collectionDemo.listDemo;

import learning.self.collectionDemo.setDemo.Student;

import java.util.*;

/**
 * Created by qiong.liu on 2018/3/24.
 * 去除list中的重复元素
 *
 * 数组转化成list  Arrays.aslist()
 *
 * list转化成数组  List.toArray()
 */
public class ListDuplicateRemove {

    public static void main(String[] args) {
        List list=new ArrayList();
        list.add(11);
        list.add(13);
        list.add(15);
        list.add(17);
        list.add(13);
//        System.out.println(duplicateRemove(list));
//        System.out.println(duplicateSetRemove(list));


        List studentList=new ArrayList();
        studentList.add(new Student("John",23));
        studentList.add(new Student("jack",25));
        studentList.add(new Student("vicky",15));
        studentList.add(new Student("jack",25));
        studentList.add(new Student("linda",31));
        studentList.add(new Student("linda",25));
        System.out.println(duplicateRemove(studentList));
        System.out.println(duplicateSetRemove(studentList));
     }


    public static List duplicateRemove(List list){

        List tempList=new ArrayList();

        for(ListIterator it=list.listIterator();it.hasNext();){
            Object obj=it.next();
            if(!tempList.contains(obj))
                tempList.add(obj); }
        list.clear();
        list.addAll(tempList);
        return list;
    }

    public static Set duplicateSetRemove(List list){
//hashSet需要依据hashCode和equals方法判断是否同数据,当使用hashSet判断对象的一致性时,需要重写equals和hashCode方法
        Set set=new HashSet();
        set.addAll(list);
        return set;
    }


}
