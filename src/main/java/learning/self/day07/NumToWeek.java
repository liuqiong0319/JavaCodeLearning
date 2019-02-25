package java.learning.self.day07;

/*
 * �����û���������ݣ���ʾ��Ӧ�����ڡ�
 */
public class NumToWeek {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String result=weekInfo(5);
		System.out.println(result);

	}
	
	public static String weekInfo(int num){
		String[] weeks={"����һ","���ڶ�","������","������","������","������","������"};
		String info="";
		if(num<=0 || num>7)
		{
			throw new RuntimeException(num+"û�ж�Ӧ������");
		}
		else
			info=num+"��Ӧ��������"+weeks[num-1];
		return info;
		
	}

}
