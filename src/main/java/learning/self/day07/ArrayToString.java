package java.learning.self.day07;

/**
 * ���幦�ܣ���{34,12,67}�����е�Ԫ��ת���ַ���  [34,12,67]
 * @author Administrator
 *
 */
public class ArrayToString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array={34,12,67};
		String result=toString(array);
		System.out.println(result);
	}

	
	public static String toString(int[] array){
		String str="[";
		for(int i=0;i<array.length;i++){
			
			if(i==array.length-1)
				str=str+array[i]+"]";
			else
				str=str+array[i]+",";
		}
		return str;
	}
}
