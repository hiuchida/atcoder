import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		long x=sc.nextLong();
		long ans=x/11;
		ans*=2;
		x%=11;
		if (x>6) {
			ans++;
			x-=6;
		}
		if (x>0) {
			ans++;
			x-=5;
		}
//		System.out.println(x);
		System.out.println(ans);
	}
	//1->5
	//2->6
	//3->6
	//4->6
	//5->6
	//6->5
}
/*
7

149696127901
*/
