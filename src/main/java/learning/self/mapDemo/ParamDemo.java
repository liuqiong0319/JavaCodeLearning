package java.learning.self.mapDemo;

/**
 * Created by qiong.liu on 2018/4/9.
 */
public class ParamDemo {

    public static void main(String[] args) {
        int sum=add(12,23,45,11);
        System.out.println(sum);
   }

    private static int add(int... arr){
        int sum=0;
        for (int i = 0; i <arr.length ; i++) {
             sum+=arr[i];
        }
        return sum;
    }
}
