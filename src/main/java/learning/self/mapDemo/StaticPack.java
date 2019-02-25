package java.learning.self.mapDemo;

import java.util.ArrayList;
import java.util.List;
import static java.util.Collections.*;//导入静态包,适合静态接口

/**
 * Created by qiong.liu on 2018/4/9.
 * 导入静态包
 */
public class StaticPack {

    public static void main(String[] args) {
        List<Integer> list=new ArrayList<Integer>();
        list.add(23);
        list.add(21);
        list.add(54);
        list.add(3);
        list.add(11);


        int max=max(list);
        int min=min(list);
    }
}
