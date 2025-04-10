import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		long n=sc.nextLong();
		long m=sc.nextLong();
		long ans=0;
		while (n>0 && n<=m/2) {
			long c=Math.max(1, n/10);
			ans+=c;
			n-=c;
			m-=c*2;
		}
//		System.out.println(n+" "+m);
		long c=m/4;
		n+=c;
		m-=c*2;
//		System.out.println(n+" "+m+" "+c);
		while (n>0 && n<=m/2) {
			c=Math.max(1, n/10);
			ans+=c;
			n-=c;
			m-=c*2;
		}
//		System.out.println(n+" "+m);
		System.out.println(ans);
	}
}
/*
1 6

12345 678901
*/
