package learning.work.first.head.test.page507;

/**
 * Created by qiong.liu on 2018/11/26.
 */
public class RyanAndMonicaJob implements Runnable {
    BankAccount bankAccount =new BankAccount();
    @Override
    public void run() {

        for (int x = 0; x < 10; x++) {
            makeWithdrawal(10);
            if (bankAccount.getBalance() < 0) {
                System.out.println("OverDrawn!!!");
            }
        }
    }

        public synchronized void makeWithdrawal(int amount){
        if (bankAccount.getBalance()>=amount){
            System.out.println(Thread.currentThread().getName()+" is running...");
            try {
                System.out.println(Thread.currentThread().getName()+" is going to sleep...");
                Thread.sleep(2000);
            }catch (Exception ex){
                ex.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" wake up...");
            bankAccount.withdraw(amount);
            System.out.println(Thread.currentThread().getName()+" completes the withdrawl");
        }
            else
            System.out.println("Sorry,not enough for "+Thread.currentThread().getName());

    }

    public static void main(String[] args) {
        RyanAndMonicaJob theJob=new RyanAndMonicaJob();
        Thread one =new Thread(theJob);
        Thread two =new Thread(theJob);

        one.setName("Ryan");
        two.setName("Monica");
        one.start();
        two.start();

    }
}
