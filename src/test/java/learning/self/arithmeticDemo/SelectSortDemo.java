package learning.self.arithmeticDemo;

import java.util.Arrays;

/**
 * Created by qiong.liu on 2018/3/19.
 *
 *
 */

/**
 * 选择排序:每次一个数与数组中其他的数值进行比较,将数值较小的往前排
 */
public class SelectSortDemo {
    public static void selectSort(int[] oldArray){
        for(int i=0;i<oldArray.length;i++){
            int k=i;
            for(int j=i+1;j<oldArray.length;j++){
                if(oldArray[k]>oldArray[j])
                    k=j;
            }
            if(k!=i){
                int temp=oldArray[k];
                oldArray[k]=oldArray[i];
                oldArray[i]=temp;
            }

        }
    }

    public static void main(String[] args) {
        int[] ListA={45,64,23,13,54,23,22,99};
        selectSort(ListA);
        System.out.println(Arrays.toString(ListA));
    }
}
