package learning.work.first.head.test.page63;

/**
 * Created by qiong.liu on 2018/9/27.
 */
public class Hobbits {
    String name;

    public static void main(String[] args) {
        Hobbits[] h = new Hobbits[3];
        int z = 0;//防止角标越界异常
        while (z < 3) {

            h[z] = new Hobbits();
            h[z].name = "bilbo";
            if (z == 1) {
                h[z].name = "frodo";
            }
            if (z == 2) {
                h[z].name = "sam";
            }
            System.out.print(h[z].name + " is a ");
            System.out.println("good Hobbit name");
            z = z + 1;

        }
    }
}
