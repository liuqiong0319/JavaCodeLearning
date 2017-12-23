package learning.self.day07;

import java.util.Arrays;

/**
 * ð������ÿ������������ֵ���������
 * @author Administrator
 *
 */
public class BubbleTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] num={15,25,2,16,11,19};
		bubble(num);
		System.out.println(Arrays.toString(num));

	}
	
	public static void bubble(int[] num){
		for(int i=0;i<num.length;i++)
		{
			for(int j=0;j<num.length-1-i;j++)
			{
				if(num[j]>num[j+1])
				{
					int temp=num[j];
					num[j]=num[j+1];
					num[j+1]=temp;
				}
			}
		}
	}

}
