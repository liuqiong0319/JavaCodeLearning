package learning.work.first.head.test.page140;

import java.util.ArrayList;

/**
 * Created by qiong.liu on 2018/10/9.
 * 游戏坐标
 */
public class DotCom {
    private String dotComeName;
    private ArrayList<String> locationCells;

    public void setDotComeName(String dotComeName) {
        this.dotComeName = dotComeName;
    }

    public void setLocationCells(ArrayList<String> locationCells) {
        this.locationCells = locationCells;
    }

    public String checkAnswer(String userGuess){
        String result ="miss";
        int index=locationCells.indexOf(userGuess);
        if (index>=0){
            locationCells.remove(index);
            if (locationCells.isEmpty()){
                result="kill";
                System.out.println("you sunk "+dotComeName);
            }
            else {
                result = "hit";
            }
        }
        return result;

    }
}
