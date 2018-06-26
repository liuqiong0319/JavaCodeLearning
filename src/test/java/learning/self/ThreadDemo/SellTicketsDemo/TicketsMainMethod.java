package learning.self.ThreadDemo.SellTicketsDemo;

/**
 * Created by qiong.liu on 2018/3/13.
 */
public class TicketsMainMethod {
    public static void main(String[] args) {
        TicketsSellMethod ticketsSellMethod=new TicketsSellMethod();
        Thread thread1=new Thread(ticketsSellMethod);
        Thread thread2=new Thread(ticketsSellMethod);
        Thread thread3=new Thread(ticketsSellMethod);
        Thread thread4=new Thread(ticketsSellMethod);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
