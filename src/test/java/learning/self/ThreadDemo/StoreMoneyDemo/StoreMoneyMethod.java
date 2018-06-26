package learning.self.ThreadDemo.StoreMoneyDemo;

/**
 *
 * 执行结果可能出现以下结果:
 * Thread-1正在存钱,当前余额为200
    Thread-0正在存钱,当前余额为200
     Thread-1正在存钱,当前余额为300
     Thread-0正在存钱,当前余额为400
    Thread-1正在存钱,当前余额为500
    Thread-0正在存钱,当前余额为600

 * Created by qiong.liu on 2018/3/13.
 */
public class StoreMoneyMethod implements Runnable {

    int money=0;

    Object object=new Object();
    public void run() {
        synchronized (object) {
            for (int i = 1; i <= 3; i++) {
                try {
                    Thread.sleep(5);
                    money += 100;
                    System.out.println(Thread.currentThread().getName() + "正在存钱,当前余额为" + money);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

    }
}
