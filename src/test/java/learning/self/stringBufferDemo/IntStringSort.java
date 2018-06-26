package learning.self.stringBufferDemo;

import java.util.Arrays;

/**
 * Created by qiong.liu on 2018/3/22.
 *
 * 23 9 -4 18 100 7
 * 要去对这串数字按照从小到大排序,生成一个数值有序的字符串
 */
public class IntStringSort {
    public static String intToStringSort(String str){
        //1.将字符串转化成数组
        StringBuffer sb=new StringBuffer();
        String[] nums=str.split(" ");//将字符串按照分隔符截取成字符数组
        int[] arrs=new int[nums.length];
        for (int i = 0; i <nums.length ; i++) {
            arrs[i]=Integer.parseInt(nums[i]);
        }
        //排序
        Arrays.sort(arrs);
        //将整型数组转化成字符串
        for (int i = 0; i <arrs.length ; i++) {
            if(i!=arrs.length-1){
                sb.append(arrs[i]+" ");
            }
            else{
               sb.append(arrs[i]) ;
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        String str="23 9 -4 18 100 7";
        String result=intToStringSort(str);
        System.out.println(result);
    }
}
