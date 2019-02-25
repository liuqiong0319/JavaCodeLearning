package learning.work.first.head.test.page211;

import java.util.ArrayList;

/**
 * Created by qiong.liu on 2018/10/17.
 */
public class AnimalListTest {

    public static void main(String[] args) {
        ArrayList<Animal> myDogArrayList=new ArrayList<Animal>();
        Dog aDog=new Dog();
        myDogArrayList.add(aDog);
        Animal d=myDogArrayList.get(0);
        System.out.println(d.getClass());
        if (d instanceof Dog){
            Dog e=(Dog) d;
            System.out.println(e.getClass());
        }

    }


}
