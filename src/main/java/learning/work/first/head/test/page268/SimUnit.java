package learning.work.first.head.test.page268;

/**
 * Created by qiong.liu on 2018/10/24.
 */
public class SimUnit {
    String botType;
    SimUnit(String botType){
        this.botType=botType;
    }
    int powerUse(){
        if ("Retention".equals(botType)){
            return 2;
        }
        else {
            return 4;
        }
    }
}
