package learning.self.day01;

/**
 * 折半查找，是对有序的数组进行查找。若不是有序数组，则直接遍历查找
 * Created by joan.liu on 2017/9/5.
 */
public class BiSourt {
    public static  int binarySort(int[] array,int key){
       int min=0;
       int max=array.length-1;
        while (max>=min)
        {
            int mid=(min+max)/2;
            if(key<array[mid])
                max=mid-1;
            else if(key>array[mid])
                min=mid+1;
            else
                return  mid;
        }
       return -1;
    }

    public static void main(String[] args) {
        int[] num ={12,13,15,21,23};
        int index=binarySort(num,15);
        System.out.println(index);
    }
}
