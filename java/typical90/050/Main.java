import java.util.*;
public class Main {
	static final long M=1000000007; //10^9+7
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int l=sc.nextInt();
		long[] dp=new long[n+1];
		dp[0]=1;
		for (int i=0; i<n; i++) {
			if (i+1<=n) dp[i+1]=modadd(dp[i+1], dp[i]);
			if (i+l<=n) dp[i+l]=modadd(dp[i+l], dp[i]);
		}
//		System.out.println(Arrays.toString(dp));
		long ans=dp[n];
		System.out.println(ans);
	}
	//valをMで割った余り
	static long mod(long val) { //ModFunc20250427
		val%=M;
		if (val<0) val+=M;
		return val;
	}
	//val+xをMで割った余り
	static long modadd(long val, long x) { //ModFunc20250427
		val=mod(val);
		x=mod(x);
		return mod(val+x);
	}
}
/*
3 2

4 4

5 2

6783 125
*/
