package learning.work.first.head.test.page207;

/**
 * Created by qiong.liu on 2018/10/12.
 */
public class MyAnimalList {
    private Animal[] animals=new Animal[5];
    private int nextIndex=0;
    public void add(Animal a){
        if (nextIndex<animals.length){
            animals[nextIndex]=a;
            System.out.println("animal added at "+nextIndex);
            nextIndex++;
        }
    }
}
