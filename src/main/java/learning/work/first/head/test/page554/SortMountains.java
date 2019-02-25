package learning.work.first.head.test.page554;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Created by qiong.liu on 2018/12/28.
 */
public class SortMountains {
    LinkedList<Mountains> mtn=new LinkedList<Mountains>();

    class NameCompare implements Comparator<Mountains>{

        @Override
        public int compare(Mountains o1, Mountains o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    class HeightCompare implements Comparator<Mountains>{

        @Override
        public int compare(Mountains o1, Mountains o2) {
            return o2.getHeight()-o1.getHeight();
        }
    }

    public static void main(String[] args) {
        new SortMountains().go();
    }

    private void go() {
        mtn.add(new Mountains("Longs",14255));
        mtn.add(new Mountains("Elbert",14433));
        mtn.add(new Mountains("Maroon",14156));
        mtn.add(new Mountains("Castle",14265));
        System.out.println("as entered :\n"+mtn);
        NameCompare nameCompare=new NameCompare();
        Collections.sort(mtn,nameCompare);
        System.out.println("as name :\n"+mtn);
        HeightCompare heightCompare=new HeightCompare();
        Collections.sort(mtn,heightCompare);
        System.out.println("as height :\n"+mtn);
    }
}
