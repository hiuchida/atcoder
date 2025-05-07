import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		final int N=2;
		int n=sc.nextInt();
		int[][] ary=new int[N][n+1];
		for (int i=0; i<N; i++) {
			for (int j=1; j<=n; j++) {
				ary[i][j]=sc.nextInt();
			}
		}
		int[][] dp=new int[2][n+1];
		for (int i=0; i<N; i++) {
			for (int j=0; j<=n; j++) {
				if (j>0) dp[i][j]=Math.max(dp[i][j], dp[i][j-1]+ary[i][j]);
				if (i>0) dp[i][j]=Math.max(dp[i][j], dp[i-1][j]+ary[i][j]);
			}
		}
//		for (int i=0; i<N; i++) {
//			System.out.println(Arrays.toString(ary[i]));
//		}
//		for (int i=0; i<N; i++) {
//			System.out.println(Arrays.toString(dp[i]));
//		}
		int ans=dp[1][n];
		System.out.println(ans);
	}
}
/*
5
3 2 2 4 1
1 2 2 2 1

4
1 1 1 1
1 1 1 1

7
3 3 4 5 4 5 3
5 3 4 4 2 3 2

1
2
3
*/
