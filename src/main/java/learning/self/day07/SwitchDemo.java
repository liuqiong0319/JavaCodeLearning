package java.learning.self.day07;

public class SwitchDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		seasonInfo(52);
		seasonIf(4);

	}
	
	public static  void seasonInfo(int mouth){
		switch (mouth){		
		case 1:
		case 2:
		case 3:
			System.out.println(mouth+"���Ǵ���");
			break;
		case 4:
		case 5:
		case 6:
			System.out.println(mouth+"�����ļ�");
			break;
		case 7:
		case 8:
		case 9:
			System.out.println(mouth+"�����＾");
			break;
		case 10:
		case 11:
		case 12:
			System.out.println(mouth+"���Ƕ���");
			break;
		default:
			System.out.println("��������·ݲ�����");
		}
	}
	
	public static void seasonIf(int mouth){
		if(mouth>=1 && mouth<=3)
			System.out.println(mouth+"���Ǵ���");
		else if(mouth>=4 && mouth<=6)
			System.out.println(mouth+"�����ļ�");
		else if(mouth>=7 && mouth<=9)
			System.out.println(mouth+"�����＾");
		else if(mouth>=10 && mouth<=12)
			System.out.println(mouth+"���Ƕ���");
		else
			System.out.println("��������·ݲ�����");
	}

}
