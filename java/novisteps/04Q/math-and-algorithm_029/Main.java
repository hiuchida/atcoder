import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		long[] dp=new long[n+1];
		dp[0]=1;
		for (int i=0; i<n; i++) {
			dp[i+1]+=dp[i];
			if (i+2<=n) dp[i+2]+=dp[i];
		}
//		System.out.println(Arrays.toString(dp));
		long ans=dp[n];
		System.out.println(ans);
	}
}
/*
4

45
*/
