import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long x = sc.nextLong();
		dp=new long[30];
		dp[1]=1;
		for (int ans=1; ans<dp.length; ans++) {
			if (calc(ans)==x) {
				System.out.println(ans);
				System.exit(0);
			}
		}
	}
	static long[] dp;
	static long calc(int n) {
		if (dp[n]>0) return dp[n];
		dp[n]=calc(n-1)*n;
		return dp[n];
	}
}
/*
6

2432902008176640000
*/
