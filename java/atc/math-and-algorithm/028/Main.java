import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ary=new int[n+1];
		for (int i=1; i<=n; i++) {
			ary[i]=sc.nextInt();
		}
		int[] dp=new int[n+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[1]=0;
		for (int i=1; i<n; i++) {
			int j=i+1;
			dp[j]=Math.min(dp[j], dp[i]+Math.abs(ary[j]-ary[i]));
			j=i+2;
			if (j<=n) {
				dp[j]=Math.min(dp[j], dp[i]+Math.abs(ary[j]-ary[i]));
			}
		}
//		System.out.println(Arrays.toString(dp));
		int ans=dp[n];
		System.out.println(ans);
	}
}
/*
4
10 30 40 20

2
10 10

6
30 10 60 10 60 50
*/
