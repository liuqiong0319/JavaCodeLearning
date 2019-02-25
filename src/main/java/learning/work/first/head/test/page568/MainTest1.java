package learning.work.first.head.test.page568;

import java.util.ArrayList;

/**
 * Created by qiong.liu on 2018/12/28.
 */
public class MainTest1 {
    public static void main(String[] args) {
        new MainTest().go();

    }

    public void go(){
        ArrayList<Animal> animals=new ArrayList<Animal>();
        animals.add(new Dog());
        animals.add(new Dog());
        takeAnimal(animals);



        ArrayList<Dog> dogs=new ArrayList<Dog>();
        dogs.add(new Dog());
        dogs.add(new Dog());
        takeAnimal(dogs);
    }

//    public void takeAnimal(ArrayList<? extends Animal> animals){
        public <T extends Animal> void  takeAnimal(ArrayList<T> animals){//和上句一个含义
        for (Animal animal:animals){
            animal.eat();
        }
    }
}
