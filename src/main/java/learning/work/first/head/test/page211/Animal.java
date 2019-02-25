package learning.work.first.head.test.page211;

/**
 * Created by qiong.liu on 2018/10/17.
 */
public class Animal {
    private Animal[] animals=new Animal[5];
    private int index=0;

    public void addAnimal(Animal a){
        if (index<animals.length){
            animals[index]=a;
            index++;
            System.out.println("animal added at "+index);

        }
    }

    @Override
    public String toString() {
        return "Animal{}";
    }
}
