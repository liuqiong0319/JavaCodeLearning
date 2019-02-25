package learning.work.first.head.test.page129;

/**
 * Created by qiong.liu on 2018/10/9.
 */
public class GuessGame {
    public static void main(String[] args) {
        int guessCount=0;
        boolean isActive=true;
        int random=(int)(Math.random()*9);
        int[] collectionCells={random,random+1,random+2};
        MatchAnswer matchAnswer=new MatchAnswer();
        matchAnswer.setLocationCells(collectionCells);
        UserGuess userGuess=new UserGuess();
        while (isActive) {
            int guessNumber=userGuess.guessNumber();
            String result = matchAnswer.matchGuess(guessNumber);
            guessCount++;

            if(result.equals("kill"))
            {
                isActive=false;
                System.out.println("Guess success,you hava guessed "+ guessCount+" times");

            }
        }

    }
}
