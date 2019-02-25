package learning.work.first.head.test.page24;

/**
 * Created by qiong.liu on 2018/9/26.
 *
 * 要求输出:a noise
            annoys
            an oyster
 */

public class PoolPuzzleOne {
    public static void main(String[] args) {
        int x=0;
        while (x<4){
            System.out.print("a");
            if(x<1){
                System.out.print(" ");
            }
            System.out.print("n");
            if (x<1) {
                System.out.print("oise");
            }
            if(x==1){
                System.out.print("noys");
            }
            if (x>1){
                System.out.println(" oyster");
                x=x+2;
            }
            System.out.println();
            x=x+1;

        }
    }
}
