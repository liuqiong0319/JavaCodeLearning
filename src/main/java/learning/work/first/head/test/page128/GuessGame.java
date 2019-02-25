package learning.work.first.head.test.page128;

/**
 * Created by qiong.liu on 2018/10/9.
 * 主函数入口
 */
public class GuessGame {
    public static void main(String[] args) {
        int userGuessCount=0;
        boolean isActive=true;//设置标志位,表示当前游戏是否需要继续进行
        MatchAnswer matchAnswer=new MatchAnswer();
        UserGuess userGuess=new UserGuess();

        int random=(int)(Math.random()*8);
        int[] collectionCells={random,random+1,random+2};
        matchAnswer.setCollectionCells(collectionCells);
        while (isActive){
            int guessInput=userGuess.guessInput();
            String result=matchAnswer.checkAnswer(guessInput);
            userGuessCount++;
            if (result.equals("kill"))
            {
                isActive=false;
                System.out.println("坐标全部猜中,用户一共猜测了:"+userGuessCount+"次");
            }

        }


    }
}
