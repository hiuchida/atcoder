import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		dp = new long[n+1];
		dp[0] = 2;
		dp[1] = 1;
		long ans=calc(n);
		System.out.println(ans);
	}
	static long[] dp;
	static long calc(int n) {
		if (dp[n]>0) return dp[n];
		long v=calc(n-2)+calc(n-1);
		dp[n]=v;
		return v;
	}
}
/*
5

86
*/
