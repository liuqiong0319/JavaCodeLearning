package learning.self.collectionDemo;

import learning.self.collectionDemo.domain.Person;
import learning.self.collectionDemo.domain.Student;
import learning.self.collectionDemo.domain.Worker;

import java.util.*;

/**
 * Created by qiong.liu on 2018/4/2.
 */
public class GenericDemo {
    public static void main(String[] args) {
        Set<Student> list = new HashSet<Student>();

        list.add(new Student("lisi1",21));
        list.add(new Student("lisi2",22));
        list.add(new Student("lisi3",23));

        printList(list);

//        List<String> list2 = new ArrayList<String>();
//
//        list2.add("lisi11");
//        list2.add("lisi22");
//        list2.add("lisi33");
//
//        printList(list2);

        List<Worker> list3 = new ArrayList<Worker>();

        list3.add(new Worker("lisi11",23));
        list3.add(new Worker("lisi22",24));
        list3.add(new Worker("lisi33",26));

        printList(list3);

    }


    public static void printList(Collection<? extends Person> list){// ?是通配符
        for(Iterator it=list.iterator();it.hasNext();){
            System.out.println(it.next());
        }
    }
}
