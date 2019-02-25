package learning.work.first.head.test.page129;

/**
 * Created by qiong.liu on 2018/10/9.
 */
public class MatchAnswer {
    int[] locationCells;
    int hitNums=0;
    public void setLocationCells(int[] locationCells){
        this.locationCells=locationCells;
    }
    public String matchGuess(int userGuess){
        String result="miss";
        for(int i=0;i<locationCells.length;i++){
            if(locationCells[i]==-1){
                result="you have hit it!!!" ;
            }
            if (locationCells[i]==userGuess ){
                    result = "hit";
                    hitNums++;
                    locationCells[i] = -1;
            }

        }
        if(hitNums==locationCells.length){
            result="kill";
        }
        System.out.println(result);
        return result;
    }
}
