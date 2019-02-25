package learning.work.first.head.test.page140;

import java.util.ArrayList;

/**
 * Created by qiong.liu on 2018/10/9.
 */
public class DotComBust {

    private GameHelper helper=new GameHelper();
    private ArrayList<DotCom> dotComsList=new ArrayList<DotCom>();
    private int guessCount=0;


    private void setUpGame(){
//        创建游戏坐标并给出相应的名称
        DotCom one=new DotCom();
        DotCom two=new DotCom();
        DotCom three=new DotCom();
        one.setDotComeName("ele.me");
        two.setDotComeName("vip.com");
        three.setDotComeName("jd.com");

        dotComsList.add(one);
        dotComsList.add(two);
        dotComsList.add(three);


        for (DotCom dotCom:dotComsList){
            ArrayList<String> newLocation=helper.placeDotCom(3);
            dotCom.setLocationCells(newLocation);
        }
    }

    private void startPlaying(){
        while (!dotComsList.isEmpty()){
            String userGuess=helper.userInput();
            checkUserGuess(userGuess);
        }
        finishGame();
    }

    private void finishGame() {
        System.out.println("you hava hit all dot coms");

        if (guessCount<=18){
            System.out.println("it only took you "+ guessCount +" times to guess");
        }
        else {
            System.out.println("took too long times:"+ guessCount+" to guess");
        }
    }

    private void checkUserGuess(String userGuess) {
        guessCount++;
        String result="miss";
        for (DotCom dotComToTest:dotComsList){
            result=dotComToTest.checkAnswer(userGuess);
            if (result.equals("hit")){
                break;
            }
            if (result.equals("kill")) {
                dotComsList.remove(dotComToTest);
                break;
            }
        }
        System.out.println(result);
    }
    public static void main(String[] args) {
        DotComBust game=new DotComBust();
        game.setUpGame();
        game.startPlaying();
    }
}
