package learning.self.day07;

import java.util.Arrays;

//ѡ������ÿ��������С������ǰ��
public class SelectionSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array={23,34,5,10,44};
//		sort(array);
		Arrays.sort(array);
		System.out.println(Arrays.toString(array));

	}
	
	public static int[] sort(int[] array){
		for(int i=0;i<array.length-2;i++)
		{
			for(int j=i+1;j<array.length-1;j++){
				if(array[i]>array[j])
				{
					int temp=array[i];
					array[i]=array[j];
					array[j]=temp;
				}
//				array[i]=(array[i]<=array[j])?array[i]:array[j];
			}
		}
		return array;
	}

}
