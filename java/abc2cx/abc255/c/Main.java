import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long x = sc.nextLong();
		long a = sc.nextLong();
		long d = sc.nextLong();
		long n = sc.nextLong();
		long l=a+d*(n-1);
		if (a>l) {
			long t=a;
			a=l;
			l=t;
			d=-d;
		}
//		System.out.println(a+" "+l);
		if (n==1) System.out.println(Math.abs(x-a));
		else if (x<a) System.out.println(a-x);
		else if (l<x) System.out.println(x-l);
		else {
			x-=a;
			x%=d;
			System.out.println(Math.min(x, d-x));
		}
	}
}
/*
6 2 3 3

0 0 0 1

998244353 -10 -20 30

-555555555555555555 -1000000000000000000 1000000 1000000000000
*/
