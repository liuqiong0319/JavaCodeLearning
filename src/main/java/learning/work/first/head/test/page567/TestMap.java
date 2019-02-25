package learning.work.first.head.test.page567;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by qiong.liu on 2018/12/28.
 */
public class TestMap {
    public static void main(String[] args) {
        Map<String,Integer> scores=new HashMap<String, Integer>();
        scores.put("Chinese",104);
        scores.put("Math",98);
        scores.put("Chemistry",103);
        scores.put("Moral",69);

        System.out.println(scores);

        System.out.println(scores.get("Math"));

    }
}
