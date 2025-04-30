import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int q=sc.nextInt();
		int[] ary=new int[n];
		final int m=30; //2^30=1,073,741,824
		int[][] dp=new int[m+1][n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
			dp[0][i]=ary[i]-1;
		}
		for (int j=1; j<=m; j++) {
			for (int i=0; i<n; i++) {
				dp[j][i]=dp[j-1][dp[j-1][i]];
			}
		}
//		for (int j=0; j<=m; j++) {
//			System.out.println(Arrays.toString(dp[j]));
//		}
		for (int qq=0; qq<q; qq++) {
			int x=sc.nextInt();
			int y=sc.nextInt();
			x--;
			int j=0;
			while (y>0) {
				if ((y&1)>0) {
					x=dp[j][x];
				}
				j++;
				y/=2;
			}
			int ans=x+1;
			System.out.println(ans);
		}
	}
}
/*
7 4
2 4 1 7 6 5 3
1 1
1 5
2 13
5 999999999
*/
