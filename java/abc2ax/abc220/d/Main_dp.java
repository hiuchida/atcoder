import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary = new int[n];
		for (int i=0; i<ary.length; i++) {
			ary[i] = sc.nextInt();
		}
		long m=998244353;
		long[][] dp=new long[n+1][10];
		dp[1][ary[0]]=1;
		for (int i=1; i<n; i++) {
			for (int j=0; j<10; j++) {
				if (dp[i][j]>0) {
					int a=(j+ary[i])%10;
					int b=(j*ary[i])%10;
					dp[i+1][a]+=dp[i][j];
					dp[i+1][b]+=dp[i][j];
					if (dp[i+1][a]>=m) dp[i+1][a]-=m;
					if (dp[i+1][b]>=m) dp[i+1][b]-=m;
				}
			}
		}
		for (int j=0; j<10; j++) {
			System.out.println(dp[n][j]);
		}
	}
}
/*
3
2 7 6

5
0 1 2 3 4
*/
