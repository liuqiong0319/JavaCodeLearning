package java.learning.self.mapDemo;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by qiong.liu on 2018/4/8.
 */
public class MapTest {
    /**
     * 练习：
     * "werertrtyuifgkiryuiop",获取字符串中每一个字母出现的次数。
     * 要求返回结果个格式是  a(1)b(2)d(4)......;
     */

    public static void main(String[] args) {
        String str="werertrtyucslkfsaifgkiryuiop";
        str=getCharCount(str);
        System.out.println(str);
    }

    private static String getCharCount(String str) {
        char[] chs=str.toCharArray();
        TreeMap<Character,Integer> treeMap=new TreeMap<Character, Integer>();

        for(int i=0;i<chs.length;i++) {
            Integer value = treeMap.get(chs[i]);
            if (!((chs[i] >= 'a' && chs[i] <= 'z') || (chs[i] >= 'A' && chs[i] <= 'Z'))) {
                //学会反向思维,当不是字母时,跳出循环,继续后续循环,这样做可以排除非字母
                continue;
            }
//            if(value==null){
//                treeMap.put(chs[i],1);
//            }
//            else{
//                value++;
//                treeMap.put(chs[i],value);
//            }

                /**
                 *
                 * get(Object key)
                 返回指定键所映射的值，如果对于该键而言，此映射不包含任何映射关系，则返回 null。
                 * 当键在不存在时,value=null,此时count++=1,将chs[i],count键值对存入到treeMap中
                 * 下次循环,键存在时,将对应的value值肤给count,再进行count++,chs[i],将chs[i],count键值对存入到treeMap中
                 * 依次循环
                 */
                int count = 0;
                if (value != null) {
                    count = value;
                }
                count++;
                treeMap.put(chs[i], count);
            }


        return treeMapToString(treeMap);
    }

    private static String treeMapToString(TreeMap<Character,Integer> treeMap) {
        /**
         * 将map集合中的键值转换成指定类型 a(1)b(2)d(4)......
         */
        //使用entrySet

        StringBuilder str=new StringBuilder();
        for (Map.Entry<Character, Integer> result : treeMap.entrySet()) {
            Character key = result.getKey();
            Integer value = result.getValue();
            str.append(key+"("+value+")");
        }
        return str.toString();
    }
}
