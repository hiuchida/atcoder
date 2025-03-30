import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long[] ary=new long[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextLong();
		}
		long ans=ary[0];
		for (int i=1; i<n; i++) {
			long gcd=gcd(ans, ary[i]);
			ans=ans/gcd*ary[i];
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
2
2
3

5
2
5
10
1000000000000000000
1000000000000000000
*/
