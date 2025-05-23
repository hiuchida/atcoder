import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		long ans=1;
		for (int i=0; i<n; i++) {
			long a=sc.nextLong();
			ans=calc_lcm(ans, a);
		}
		System.out.println(ans);
	}
	static long calc_lcm(long a, long b) {
		long gcd=calc_gcd(a, b);
		return a/gcd*b;
	}
	static long calc_gcd(long a, long b) {
		while (a>=1 && b>=1) {
			if (a>b) a%=b;
			else b%=a;
		}
		if (a>=1) return a;
		return b;
	}
}
/*
3
12 18 14
*/
