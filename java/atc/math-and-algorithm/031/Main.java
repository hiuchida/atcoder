import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ary=new int[n+1];
		for (int i=1; i<=n; i++) {
			ary[i]=sc.nextInt();
		}
		long[][] dp=new long[n+1][2];
		dp[0][1]=Long.MIN_VALUE;
		for (int i=1; i<=n; i++) {
			dp[i][0]=Math.max(dp[i][0], dp[i-1][0]);
			dp[i][0]=Math.max(dp[i][0], dp[i-1][1]);
			dp[i][1]=Math.max(dp[i][1], dp[i-1][1]);
			dp[i][1]=Math.max(dp[i][1], dp[i-1][0]+ary[i]);
		}
//		for (int i=0; i<=n; i++) {
//			System.out.println(Arrays.toString(dp[i]));
//		}
		long ans=Math.max(dp[n][0], dp[n][1]);
		System.out.println(ans);
	}
}
/*
5
2 5 3 3 1
*/
