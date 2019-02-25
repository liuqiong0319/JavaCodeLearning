package learning.work.first.head.test.page139;

import java.util.ArrayList;

/**
 * Created by qiong.liu on 2018/10/8.
 * 比较用户输入与实际数字
 */
public class SimpleDotCome {

    private ArrayList<Integer> collectionCells;

    public void setCollectionCells(ArrayList<Integer> collectionCells) {
        this.collectionCells = collectionCells;
    }

    public String checkAnswer(Integer userGuess) {
//        方法1:检查arrayList中是否包含用户猜测的数字,若包含,则移除.移除完成后判断list是否为空,若为空则返回kill ,否则返回hit;
//        注意:remove方法中,若输入的是int类型,则表示移除指定位置上的元素,此时返回的是从列表中移除的元素;若需要移除次列表中首次出现的指定元素,则需要使用此方法:boolean remove(Object o)
        String result="miss";
        if (collectionCells.contains(userGuess)){
            collectionCells.remove(userGuess);
            if (collectionCells.isEmpty()) {
                result="kill";
            }
            else{
                result="hit";
            }
        }
        System.out.println(result);
        return result;

    }

    public String checkAnswer1(Integer userGuess) {

        //方法2:将用户的猜测的数字与list进行匹配,若不存在则返回index=-1
        String result = "miss";
        int index = collectionCells.indexOf(userGuess);
        if (index >= 0)//表示匹配到了坐标
        {
            collectionCells.remove(index);
            if (collectionCells.isEmpty()) {
                result = "kill";
            } else
                result = "hit";
        }
        System.out.println(result);
        return result;
    }
}
