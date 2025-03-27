import java.util.*;
public class Main {
	static final long M=998244353;
	static long mod(long val) {
		return val%M;
	}
	static int modadd(long val, long x) {
		return (int)mod(val+x);
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int k = sc.nextInt();
		int[][] dp=new int[n+1][k+1];
		dp[0][0]=1;
		for (int h=1; h<=n; h++) {
			for (int i=0; i<=k; i++) {
				for (int j=1; j<=m; j++) {
					if (i+j>k) break;
					dp[h][i+j]=modadd(dp[h][i+j], dp[h-1][i]);
				}
			}
		}
//		for (int i=0; i<=n; i++) {
//			System.out.println(Arrays.toString(dp[i]));
//		}
		int ans=0;
		for (int i=1; i<=k; i++) {
			ans=modadd(ans, dp[n][i]);
		}
		System.out.println(ans);
	}
}
/*
2 3 4

31 41 592
*/
