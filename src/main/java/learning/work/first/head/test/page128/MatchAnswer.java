package learning.work.first.head.test.page128;

/**
 * Created by qiong.liu on 2018/10/9.
 * 将用户猜测答案与实际坐标进行对比,并返回相应的对比结果:miss\hit\kill
 */
public class MatchAnswer {
    private  int [] collectionCells;//定义坐标
    int hitCount;//定义击中次数
    String result ="miss";
    boolean[] flag={false,false,false};
    public    void setCollectionCells(int[] collectionCells){//设置坐标
        this.collectionCells=collectionCells;
    }

    public String checkAnswer(int userGuess){
        for (int i=0;i<collectionCells.length;i++) {
            if (collectionCells[i] == userGuess) {
                if(flag[i]==false) {
                    result = "hit";
                    flag[i] = true;
                    hitCount++;
                }
                else{
                    result="you hava hit it";

                }
            }
        }
            if (hitCount==collectionCells.length){
                result="kill";
            }
        System.out.println(result);
        return result;
    }
}
