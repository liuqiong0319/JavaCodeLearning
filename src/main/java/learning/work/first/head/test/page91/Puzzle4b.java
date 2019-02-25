package learning.work.first.head.test.page91;

/**
 * Created by qiong.liu on 2018/9/27.
 */
public class Puzzle4b {
    int ivar;
    public int doStuff(int factor){
        if(ivar>100){
            return ivar*factor;
        }else{
            return ivar*(5-factor);
        }
    }
}
