import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long ans=0;
		for (int i=0; i<n; i++) {
			int a=sc.nextInt();
			ans=gcd(ans, a);
		}
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
4
2 10 8 40

4
5 13 8 1000000000

3
1000000000 1000000000 1000000000
*/
