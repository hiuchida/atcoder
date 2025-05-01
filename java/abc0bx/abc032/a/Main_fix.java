import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int n = sc.nextInt();
		long gcd=gcd(a, b);
		long lcm=a*b/gcd;
		for (int i=1; true; i++) {
			long ans=lcm*i;
			if (ans>=n) {
				System.out.println(ans);
				System.exit(0);
			}
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
2
3
8

2
2
2

12
8
25
*/
