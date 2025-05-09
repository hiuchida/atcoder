import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		int x=1900*m+100*(n-m);
		int p=1;
		while (m>0) {
			p*=2;
			m--;
		}
//		System.out.println(x+" "+p);
		int ans=x*p;
		System.out.println(ans);
	}
}
/*
1 1

10 2

100 5
*/
