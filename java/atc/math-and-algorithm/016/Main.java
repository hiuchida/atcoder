import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		long ans=0;
		for (int i=0; i<n; i++) {
			long a=sc.nextLong();
			ans=calc_gcd(ans, a);
		}
		System.out.println(ans);
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
12 18 24
*/
