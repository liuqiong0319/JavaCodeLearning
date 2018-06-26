package learning.self.collectionDemo.setDemo;

import java.util.Iterator;
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
 2，比较器排序，其实就是在创建TreeSet集合时，在构造函数中指定具体的比较方式。
 需要定义一个类实现Comparator接口，重写compare方法。
 *
 *
 * treeSet使用的是二叉树
 */
public class TreeSetMethod2 {
    public static void main(String[] args) {
//        TreeSet treeSet=new TreeSet(new ComPareByName());
//        TreeSet treeSet=new TreeSet(new CompareByAge());
//        TreeSet treeSet=new TreeSet(new CompareByLength());
        TreeSet treeSet=new TreeSet(new CompareByAnyway());
//        treeSet.add("hello");
//        treeSet.add("are");
//        treeSet.add("you");
//        treeSet.add("ok");


        treeSet.add(new Student("lisa6",21));
        treeSet.add(new Student("lisaCo8",22));
        treeSet.add(new Student("lisaJ6",21));
        treeSet.add(new Student("lisaLinda3",23));
        treeSet.add(new Student("lisa7",22));

//
//      for (Iterator it = treeSet.iterator(); it.hasNext();){
//            System.out.println(it.next());
//        }

        /*
		 * foreach：其实就是增强for循环。
		 * 格式：
		 * for(元素的数据类型  变量   : Collection集合or数组){}
		 * 用于遍历Collection或数组。通常只能遍历元素，不要在遍历的过程中做对集合元素的操作。
		 *
		 * 和老式的for循环有什么区别？
		 * 注意：新for循环必须有被遍历的目标。目标只能是Collection或者是数组。
		 * 建议：遍历数组时，如果仅为遍历，可以使用增强for如果要对数组的元素进行 操作，使用老式for循环可以通过角标操作。
		 *
		 */

        for(Object obj:treeSet){
            System.out.println(obj);
        }





    }
}
