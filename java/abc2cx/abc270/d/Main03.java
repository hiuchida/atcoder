import java.util.*;
public class Main {
	static int n;
	static int[] ary;
	static long[][] dp;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		int k = sc.nextInt();
		ary=new int[n];
		for (int i=0; i<k; i++) {
			ary[i]=sc.nextInt();
		}
		dp=new long[2][n+1];
		for (int i=0; i<2; i++) {
			Arrays.fill(dp[i], -1);
		}
		dp[0][0]=0;
		dp[1][0]=0;
		for (int j=0; j<n; j++) {
			for (int i=0; i<k; i++) {
				int a=ary[i];
				if (dp[1][j]>=0) {
					if (j+a<=n) dp[0][j+a]=Math.max(dp[0][j+a], dp[1][j]+a);
				}
				if (dp[0][j]>=0) {
					if (j+a<=n) {
						if (dp[1][j+a]<0) dp[1][j+a]=dp[0][j];
//						dp[1][j+a]=Math.max(dp[1][j+a], dp[0][j]);
					}
				}
			}
		}
//		print();
		long ans=Math.max(dp[0][n], dp[1][n]);
		System.out.println(ans);
	}
	static void print() {
		for (int i=0; i<2; i++) {
			System.out.print(i+": ");
			for (int j=0; j<=n; j++) {
				System.out.print(String.format("%2d ", dp[i][j]));
			}
			System.out.println();
		}
	}
}
/*
10 2
1 4

11 4
1 2 3 6

10000 10
1 2 4 8 16 32 64 128 256 512
*/
