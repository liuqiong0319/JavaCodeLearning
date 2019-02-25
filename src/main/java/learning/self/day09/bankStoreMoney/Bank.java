package java.learning.self.day09.bankStoreMoney;

import lombok.Data;
import lombok.Getter;

/**
 * Created by qiong.liu on 2017/12/23.
 */
@Getter
public class Bank {
    private int total;

    public int getTotal() {
        return total;
    }

    public void store(int money){
        total=total+money;
        System.out.println("银行余额:"+total);
    }
}
