package java.learning.self.day07;

/**
 * ����ʮ����-->ʮ�����ơ�
 * @author Administrator
 *
 */

public class scaleOf16 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String result=trance(3569);
		System.out.println(result);

	}
	
	public static String trance(int num){
		char[] result=new char[8];
		int index=result.length;
		String str="";
		while(num!=0)
		{
			int temp=num & 15;
			if(temp>9)
				result[--index]=(char)(temp-10+'a');
			else 
				result[--index]=(char)(temp+'0');
			num=num>>>4;
		}	
		str=toString(result,index);
		return str;
	}
	
	public static String toString(char[] arr,int index){
		String temp="0x";
		for(int i=index;i<arr.length;i++)
		{
			temp=temp+arr[i];
		}
		return temp;
	}

}
