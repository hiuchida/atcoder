import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		long n=(long)1e18;
		long x=sc.nextLong();
		long y=sc.nextLong();
		long gcd=gcd(x, y);
		long xx=x/gcd;
		long yy=y/gcd;
//		System.out.println(xx+" "+yy+" "+gcd);
		long ans=xx*gcd;
		if (ans>n/yy) System.out.println("Large");
		else {
			ans*=yy;
			System.out.println(ans);
		}
	}
	//aとbの最大公約数
	static long gcd(long a, long b) {
		if (b<a) {
			long t=a;
			a=b;
			b=t;
		}
		while (a>0) {
			long t=b%a;
			b=a;
			a=t;
		}
		return b;
	}
}
/*
4 6

1000000000000000000 3

1000000000000000000 1
*/
