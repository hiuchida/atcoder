import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		long n=sc.nextLong();
		long m=sc.nextLong();
		long ans=0;
		if (n*2<=m) {
			ans+=n;
			m-=n*2;
			n=0;
		}
//		System.out.println(n+" "+m);
		long c=m/4;
		n+=c;
		m-=c*2;
//		System.out.println(n+" "+m+" "+c);
		ans+=c;
		n-=c;
		m-=c*2;
//		System.out.println(n+" "+m);
		System.out.println(ans);
	}
}
/*
1 6

12345 678901
*/
