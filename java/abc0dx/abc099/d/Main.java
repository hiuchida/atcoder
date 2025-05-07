import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int c=sc.nextInt();
		int[][] ad=new int[c][c];
		for (int i=0; i<c; i++) {
			for (int j=0; j<c; j++) {
				ad[i][j]=sc.nextInt();
			}
		}
		int[][] ac=new int[n][n];
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				ac[i][j]=sc.nextInt();
			}
		}
//		for (int i=0; i<c; i++) {
//			System.out.println(Arrays.toString(ad[i]));
//		}
//		for (int i=0; i<n; i++) {
//			System.out.println(Arrays.toString(ac[i]));
//		}
		int[][] dp=new int[3][c];
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				int idx=(i+1+j+1)%3;
				for (int k=0; k<c; k++) {
					dp[idx][k]+=ad[ac[i][j]-1][k];
				}
			}
		}
//		for (int i=0; i<3; i++) {
//			System.out.println(Arrays.toString(dp[i]));
//		}
		int ans=Integer.MAX_VALUE;
		for (int i=0; i<c; i++) {
			for (int j=0; j<c; j++) {
				if (i==j) continue;
				for (int k=0; k<c; k++) {
					if (i==k || j==k) continue;
					int x=dp[0][i]+dp[1][j]+dp[2][k];
					ans=Math.min(ans, x);
				}
			}
		}
		System.out.println(ans);
	}
}
/*
2 3
0 1 1
1 0 1
1 4 0
1 2
3 3

4 3
0 12 71
81 0 53
14 92 0
1 1 2 1
2 1 1 2
2 2 1 3
1 1 2 2
*/
