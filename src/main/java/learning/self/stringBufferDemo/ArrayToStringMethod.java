package java.learning.self.stringBufferDemo;

/**
 * Created by qiong.liu on 2018/3/21.
 * 将数组转化成字符串存储
 */
public class ArrayToStringMethod {

    public static StringBuffer transToString(int[] arrayLists){
        StringBuffer str=new StringBuffer().append('[');
        for (int i = 0; i <arrayLists.length ; i++) {
            if(i==arrayLists.length-1){
              str.append(arrayLists[i]).append(']');
            }
            else
            str.append(arrayLists[i]).append(',');
        }
        return str;
    }

    public static void main(String[] args) {
        int[] arrayList={12,34,23,42,56};
        StringBuffer str=transToString(arrayList);
        System.out.println(str);
    }
}
