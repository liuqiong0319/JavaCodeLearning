package learning.work.first.head.test.page193;

/**
 * Created by qiong.liu on 2018/10/12.
 */
public class MonsterTestDriver {
    public static void main(String[] args) {
        Monster[] ma=new Monster[3];
        ma[0]=new Vampire();
        ma[1]=new Dragon();
        ma[2]=new Monster();

        for (int x=0;x<3;x++){
            ma[x].frighten(x);
        }
    }
}
