import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] aa=new int[n+1];
		int[] ab=new int[n+1];
		int[] ac=new int[n+1];
		for (int i=1; i<=n; i++) {
			aa[i]=sc.nextInt();
			ab[i]=sc.nextInt();
			ac[i]=sc.nextInt();
		}
		long[][] dp=new long[n+1][3];
		for (int i=1; i<=n; i++) {
			dp[i][0]=Math.max(dp[i][0], dp[i-1][1]+ab[i]);
			dp[i][0]=Math.max(dp[i][0], dp[i-1][2]+ac[i]);
			dp[i][1]=Math.max(dp[i][1], dp[i-1][0]+aa[i]);
			dp[i][1]=Math.max(dp[i][1], dp[i-1][2]+ac[i]);
			dp[i][2]=Math.max(dp[i][2], dp[i-1][0]+aa[i]);
			dp[i][2]=Math.max(dp[i][2], dp[i-1][1]+ab[i]);
		}
//		for (int i=0; i<=n; i++) {
//			System.out.println(Arrays.toString(dp[i]));
//		}
		long ans=Math.max(dp[n][0], dp[n][1]);
		ans=Math.max(ans, dp[n][2]);
		System.out.println(ans);
	}
}
/*
3
10 40 70
20 50 80
30 60 90

1
100 10 1

7
6 7 8
8 8 3
2 5 2
7 8 6
4 6 8
2 3 4
7 5 1
*/
