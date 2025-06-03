import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int w=sc.nextInt();
		int[] aw=new int[n];
		int[] av=new int[n];
		for (int i=0; i<n; i++) {
			aw[i]=sc.nextInt();
			av[i]=sc.nextInt();
		}
		long[][] dp=new long[n+1][w+1];
		for (int i=0; i<=n; i++) {
			Arrays.fill(dp[i], Long.MIN_VALUE);
		}
		dp[0][0]=0;
		for (int i=1; i<=n; i++) {
			for (int j=0; j<=w; j++) {
				dp[i][j]=Math.max(dp[i][j], dp[i-1][j]);
				if (j-aw[i-1]>=0) dp[i][j]=Math.max(dp[i][j], dp[i-1][j-aw[i-1]]+av[i-1]);
			}
		}
//		for (int i=0; i<=n; i++) {
//			for (int j=0; j<=w; j++) {
//				System.out.print(dp[i][j]>=0 ? dp[i][j] : -1);
//				System.out.print(" ");
//			}
//			System.out.println();
//		}
		long ans=0;
		for (int j=0; j<=w; j++) {
			ans=Math.max(ans, dp[n][j]);
		}
		System.out.println(ans);
	}
}
/*
3 8
3 30
4 50
5 60

5 5
1 1000000000
1 1000000000
1 1000000000
1 1000000000
1 1000000000

6 15
6 5
5 6
6 4
6 6
3 5
7 2
*/
