package java.learning.self.ioDemo;

/**
 * Created by qiong.liu on 2018/4/17.
 * 递归
 */
public class RecursionDemo {

    public static void main(String[] args) {
        int sum=add(4);
        System.out.println(sum);


    }


    public static int add(int num){
        if(num==1){
            return num;
        }
        return num+add(num-1);
    }
}
