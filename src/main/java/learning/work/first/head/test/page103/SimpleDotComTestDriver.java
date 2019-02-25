package learning.work.first.head.test.page103;

/**
 * Created by qiong.liu on 2018/10/8.
 * 测试代码
 */
public class SimpleDotComTestDriver {

    public static void main(String[] args) {
        SimpleDotCom simpleDotCom = new SimpleDotCom();
        int[] locations = {4, 5, 6};
        simpleDotCom.setLocationCells(locations);

        int userGuess = 2;
        String result = simpleDotCom.checkYourself(userGuess);
    }
}
