package learning.self.day07;

public class CompareDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int result=compare(5, 23, 7);
		System.out.println(result);

	}
	
	public static int compare(int a,int b,int c){
		int max=0;
		max=a>b?a:b;
		max=max>c?max:c;
		return max;
	}

}
