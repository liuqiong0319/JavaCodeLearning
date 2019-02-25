package learning.work.first.head.test.page268;

import java.util.ArrayList;

/**
 * Created by qiong.liu on 2018/10/24.
 */
public class V2Radiator {
    V2Radiator(ArrayList list){
        for (int x=0;x<5;x++){
            list.add(new SimUnit("V2Radiator"));
            System.out.println("V2Radiator"+x);
        }
    }
}
