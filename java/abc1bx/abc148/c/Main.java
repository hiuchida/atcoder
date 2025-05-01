import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		long gcd=gcd(a, b);
		long ans=a/gcd*b;
		System.out.println(ans);
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
2 3

123 456

100000 99999
*/
