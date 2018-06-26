package learning.self.collectionDemo.setDemo;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by qiong.liu on 2018/3/26.
 * TreeSet实现自然排序
 * * TreeSet的add方法内部最终实现：
 * 需要将元素转成Comparable类型，为什么？因为这个类型具备排序的能力。
 * 这个类型中有一个专门为排序提供了一个compareTo方法。
 * 如果要让学生具备比较排序的功能，需要让学生扩展功能，实现Comparable接口。
 * treeSet比较元素是否相同,是根据结果是否为0来判断.
 *
 * 元素的排序比较有两种方式：
 1，元素自身具备自然排序，其实就是实现了Comparable接口重写了compareTo方法。
 如果元素自身不具备自然排序，或者具备的自然排序不是所需要的，这时只能用第二种方式。
 *
 *
 * treeSet使用的是二叉树
 */
public class TreeSetMethod {
    public static void main(String[] args) {
        TreeSet treeSet=new TreeSet();
//        treeSet.add("hello");
//        treeSet.add("are");
//        treeSet.add("you");
//        treeSet.add("ok");


        treeSet.add(new Student("lisa6",21));
        treeSet.add(new Student("lisa8",22));
        treeSet.add(new Student("lisa6",21));
        treeSet.add(new Student("lisa3",23));
        treeSet.add(new Student("lisa7",22));


        //未实现Comparable接口时,会抛出异常:learning.self.collectionDemo.setDemo.Student cannot be cast to java.lang.Comparable
        for (Iterator it = treeSet.iterator(); it.hasNext();){
            System.out.println(it.next());
        }






    }
}
