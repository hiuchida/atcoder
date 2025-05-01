import java.math.BigDecimal;
import java.math.*;
import java.util.*;
public class Main {
	static int MAX=(int)3e6;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		System.out.println(MAX);
		int t = sc.nextInt();
		for (int tt=0; tt<t; tt++) {
			long n = sc.nextLong();
			long x=findByFloyd(n);
//			System.out.println(n+" "+x);
			if (n/x/x*x*x==n) {
				long p=x;
				long q=n/p/p;
				System.out.println(p+" "+q);
			} else {
				long p1=(long)Math.sqrt(n/x);
				long q1=n/p1/p1;
				long p2=(long)Math.sqrt(x);
				long q2=n/p2/p2;
				if (p1*p1*q1==n) {
					System.out.println(p1+" "+q1);
				} else if (p2*p2*q2==n) {
					System.out.println(p2+" "+q2);
				} else {
					System.out.println("?");
				}
			}
		}
	}
	static long findByFloyd(long n) {
		if (n%2==0) return 2;
		for (long c=1; c<=n; c++) {
			long x=0;
			long y=0;
			long g=1;
			while (g==1) {
				x=findByFloydFunc(x, c, n);
				y=findByFloydFunc(findByFloydFunc(x, c, n), c, n);
				g=gcd(Math.abs(x-y), n);
			}
			if (g==n) continue;
			return g;
		}
		return -1;
	}
	static long findByFloydFunc(long a, long c, long n) {
//		BigInteger ba=BigInteger.valueOf(a);
//		BigInteger bc=BigInteger.valueOf(c);
//		BigInteger bn=BigInteger.valueOf(n);
//		BigInteger b2=ba.pow(2);
//		BigInteger b3=b2.add(bc);
//		BigInteger b4=b3.mod(bn);
//		return b4.longValue();
		return (a*a+c)%n;
	}
//	static long gcd(long a, long b) {
//		if (a < b) {
//			if (a == 0) return b;
//			else return gcd(a, b % a);
//		}
//		if (b == 0) return a;
//		else return gcd(b, a % b);
//	}
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
3
2023
63
1059872604593911
*/
/*
1
289
*/
