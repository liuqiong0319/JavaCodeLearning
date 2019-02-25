package learning.work.first.head.test.page103;

/**
 * Created by qiong.liu on 2018/10/8.
 * 处理用户猜测与实际结果是否相符,并返回相应的结果
 */
public class SimpleDotCom {
    int [] locationCells;
    int numOfHits=0;
    public String checkYourself(int userGuess) {//通过获取用户的猜测结果与实际坐标进行比较,若相等,则表示击中,返回hit,同时击中次数加1
        String result="miss";//默认设置其未击中,即为miss
//        for (int i = 0; i < locationCells.length; i++) {
        for(int cell:locationCells){
            if(userGuess==cell){
//            if (userGuess==locationCells[i]) {
//                if(checkYourself(userGuess)=="hit"){
//                    System.out.println("you hava hit it");
//                    break;
//                }
                result = "hit";
                numOfHits++;
                break;
            }
        }
        if(numOfHits==locationCells.length){//当击中次数与数组长度一致时,表示全部击中,返回kill
            result="kill";
        }
        System.out.println(result);
        return result;
    }

    void setLocationCells(int[] locationCells){
        this.locationCells=locationCells;
    }
}
