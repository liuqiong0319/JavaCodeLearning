package java.learning.self.day07;

public class ForDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		multi(9,9);
		multi2(4);
		show(7);
	}

	public static void multi(int a,int b){
		for(int i=1;i<=a;i++){
			for(int j=i;j<=b;j++)
			{
				System.out.print(i+"*"+j+"="+i*j+"\t");
			}
			System.out.println();
		}
	}
	//�˷���
	public static void multi2(int a){
		for(int i=1;i<=a;i++){
			for(int j=1;j<=i;j++)
			{
				System.out.print(j+"*"+i+"="+i*j+"\t");
			}
			System.out.println();
		}
	}
	
	//���ֵ�������ʾ
	public static void show(int a){
		for(int i=1;i<=a;i++){
			for(int j=a;j>=i;j--)
			{
				System.out.print(j);
			}
			System.out.println();
		}
	}
}
