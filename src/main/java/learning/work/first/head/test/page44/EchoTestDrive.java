package learning.work.first.head.test.page44;

/**
 * Created by qiong.liu on 2018/9/27.
 */
public class EchoTestDrive {
    public static void main(String[] args) {
        Echo e1=new Echo();
//        Echo e2=new Echo();//结果为10的输入
        Echo e2=e1;//结果为24的输入
        int x=0;
        while(x<4){
            e1.hello();
            e1.count=e1.count+1;
            if(x==3){
                e2.count=e2.count+1;
            }
            if(x>0){
                e2.count=e2.count+e1.count;
            }
            x=x+1;
        }
        System.out.println(e2.count);
        int y=(int)(Math.random()*100);
        System.out.println(y);
    }
}
