package learning.self.ThreadDemo.StoreMoneyDemo;

/**
 * Created by qiong.liu on 2018/3/13.
 */
public class StoreMoneyMain {
        public static void main(String[] args) {
            StoreMoneyMethod storeMoneyMethod=new StoreMoneyMethod();
            Thread thread1=new Thread(storeMoneyMethod);
            Thread thread2=new Thread(storeMoneyMethod);
            thread1.start();
            thread2.start();
        }

}
