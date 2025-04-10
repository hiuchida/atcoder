import java.util.*;
public class Main {
	public static void main(String[] args) {
		long N=(long)1e12;
//		Scanner sc=new Scanner(System.in);
//		long n=sc.nextLong();
//		long m=sc.nextLong();
		for (long n=1; n<1000; n++) {
			for (long m=1; m<1000; m++) {
				long ret1=main1(n, m);
				long ret99=main99(n, m);
				if (ret1!=ret99) {
					System.out.println(n+" "+m);
					System.out.println(ret1);
					System.out.println(ret99);
					n=N;
					break;
				}
			}
		}
	}
	public static long main1(long n, long m) {
//		Scanner sc=new Scanner(System.in);
//		long n=sc.nextLong();
//		long m=sc.nextLong();
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
		return ans;
	}
	public static long main99(long n, long m) {
//		Scanner sc=new Scanner(System.in);
//		long n=sc.nextLong();
//		long m=sc.nextLong();
		long ans=0;
		if (n*2>m) {
			ans=m/2;
			return ans;
		}
		ans+=n;
		m-=n*2;
		ans+=m/4;
		return ans;
	}
}
/*
1 6

12345 678901
*/
