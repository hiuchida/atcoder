import java.util.*;
public class Main {
	static final long M=998244353;  //10^9-1755647
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
					dp[h][i+j]=(int) modadd(dp[h][i+j], dp[h-1][i]);
				}
			}
		}
//		for (int i=0; i<=n; i++) {
//			System.out.println(Arrays.toString(dp[i]));
//		}
		int ans=0;
		for (int i=1; i<=k; i++) {
			ans=(int) modadd(ans, dp[n][i]);
		}
		System.out.println(ans);
	}
	//valをMで割った余り
	static long mod(long val) { //ModFunc20250421
		return val%M;
	}
	//val+xをMで割った余り
	static long modadd(long val, long x) { //ModFunc20250421
		return mod(val+x);
	}
}
/*
2 3 4

31 41 592
*/
