package learning.work.first.head.test.page119;

/**
 * Created by qiong.liu on 2018/10/8.
 */
public class MultiFor {
    public static void main(String[] args) {
        for (int x=0;x<4;x++)
        {

            for (int y=4;y>2;y--)
            {
                System.out.println(x+" "+y);
            }
            if (x==1){
                x++;
            }
        }
    }
}
