package learning.work.first.head.test.page38;

/**
 * Created by qiong.liu on 2018/9/26.
 */
public class GuessGame {

    Player p;//对象
    int number;//谜底


    public void startGame() {
        p=new Player();
        number=(int)(Math.random()*10);
        System.out.println("number of guess is "+number);
        while (true) {
            p.guess();
            if (p.guessNumber == number) {

                System.out.println("Guess Right");
                System.out.println("Game over");
                break;
            } else {
                System.out.println("Guess Wrong");
            }


        }
    }
}
