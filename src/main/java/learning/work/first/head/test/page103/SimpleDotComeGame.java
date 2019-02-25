package learning.work.first.head.test.page103;

/**
 * Created by qiong.liu on 2018/10/8.
 * 主函数入口
 */
public class SimpleDotComeGame {
    public static void main(String[] args) {
        int numOfGuess=0;//设置用户猜测次数,默认为0;
        GameHelper gameHelper=new GameHelper();
        SimpleDotCom simpleDotCom=new SimpleDotCom();
        int randomNum=(int)(Math.random()*5);//获取0~4之间的随机数.Math.random会返回介于0~1之间的数.
        int[] locations={randomNum,randomNum+1,randomNum+2};//设置三个连续数字作为需击中目标的坐标位置
        simpleDotCom.setLocationCells(locations);
        boolean isActive=true;//设置标志位,标识用户是否需要继续猜测以击中目标
        while (isActive){
            int guess=gameHelper.getUserInput();
            String result=simpleDotCom.checkYourself(guess);
            numOfGuess++;
            if(result.equals("kill")){//当返回kill时,标识目标被击沉
                isActive=false;//此时isActive=false并退出游戏.
                System.out.println("Guess success,you hava guessed "+ numOfGuess+" times");
            }
        }
    }
}
