import java.util.*;
public class Main {
	static final long M=1000000007; //10^9+7
	static long[] dp;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		if (Math.abs(n-m)>1) {
			System.out.println(0);
			System.exit(0);
		}
		init(Math.max(n, m));
//		System.out.println(Arrays.toString(dp));
		long x1=dp[n];
		long x2=dp[m];
		long ans=modmul(x1, x2);
		if (n==m) ans=modmul(ans, 2);
		System.out.println(ans);
	}
	static void init(int n) {
		dp=new long[n+1];
		dp[1]=1;
		for (int i=2; i<=n; i++) {
			dp[i]=modmul(dp[i-1], i);
		}
	}
	//valをMで割った余り
	static long mod(long val) { //ModFunc20250421
		return val%M;
	}
	//val*xをMで割った余り
	static long modmul(long val, long x) { //ModFunc20250421
		return mod(val*x);
	}
}
/*
2 2

3 2

1 8

100000 100000
*/
