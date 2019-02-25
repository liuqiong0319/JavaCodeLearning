package learning.work.first.head.test.page507;

/**
 * Created by qiong.liu on 2018/11/26.
 */
public class BankAccount {
    private  int balance=100;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
    public void withdraw(int amount){
        balance=balance-amount;

    }
}
