package learning.work.first.head.test.page139;


import java.util.ArrayList;

/**
 * Created by qiong.liu on 2018/10/8.
 * 主函数入口
 */
public class SimpleDotComeGame {
    public static void main(String[] args) {
        int numOfGuess=0;//设置用户猜测次数,默认为0;
        GuessInput guessInput=new GuessInput();
        SimpleDotCome simpleDotCom=new SimpleDotCome();
        int random=(int)(Math.random()*8);
        ArrayList<Integer> locationCells=new ArrayList<Integer>();
        locationCells.add(random);
        locationCells.add(random+1);
        locationCells.add(random+2);
        simpleDotCom.setCollectionCells(locationCells);
        boolean isActive=true;
        while(isActive){
            int answer=guessInput.getGuessNumber();
             String result=simpleDotCom.checkAnswer1(answer);
            numOfGuess++;
            if (result.equals("kill"))
            {
                isActive=false;//此时isActive=false并退出游戏.
                System.out.println("Guess success,you hava guessed "+ numOfGuess+" times");
            }
        }
    }
}
