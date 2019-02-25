package learning.work.first.head.test.page268;

import java.util.ArrayList;

/**
 * Created by qiong.liu on 2018/10/24.
 */
public class V3Radiator extends V2Radiator {
    V3Radiator(ArrayList list) {
        super(list);
        for (int g=0;g<10;g++){
            list.add(new SimUnit("V3Radiator"));
            System.out.println("V3Radiator"+g);

        }
    }
}
