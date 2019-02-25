package learning.work.first.head.test.page568;

/**
 * Created by qiong.liu on 2018/12/28.
 */
public class MainTest {
    public static void main(String[] args) {
        new MainTest().go();
    }

    public void go(){
        Animal animal[]={new Dog(),new Cat(),new Dog()};
        Dog dog[]={new Dog(),new Dog(),new Dog()};
        takeAnimal(animal);
        takeAnimal(dog);
    }


    public void takeAnimal(Animal[] animals){
        for (Animal a:animals){
            a.eat();
        }
    }
}
