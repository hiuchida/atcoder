import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int x = sc.nextInt();
		long ans=0;
		for (int i=0; i<n; i++) {
			int a=sc.nextInt();
			ans=gcd(ans, Math.abs(x-a));
		}
		System.out.println(ans);
	}
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
3 3
1 7 11

3 81
33 105 57

1 1
1000000000
*/
