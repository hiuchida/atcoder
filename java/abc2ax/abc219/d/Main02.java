import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int x = sc.nextInt();
		int y = sc.nextInt();
		int[] aa = new int[n];
		int[] ab = new int[n];
		int suma = 0;
		int sumb = 0;
		for (int i=0; i<n; i++) {
			aa[i] = sc.nextInt();
			ab[i] = sc.nextInt();
			suma+=aa[i];
			sumb+=ab[i];
		}
		if (suma<x || sumb<y) {
			System.out.println(-1);
			System.exit(0);
		}
		int size=300*2;
		int[][] dp = new int[size+1][size+1];
		for (int i=0; i<=size; i++) {
			Arrays.fill(dp[i], n+1);
		}
		dp[0][0]=0;
		for (int i=0; i<=x; i++) {
			for (int j=0; j<=y; j++) {
				for (int k=0; k<n; k++) {
					int dx=aa[k];
					int dy=ab[k];
					if (i+dx>size || j+dy>size) continue;
					dp[i+dx][j+dy]=Math.min(dp[i+dx][j+dy], dp[i][j]+1);
				}
			}
		}
//		for (int i=0; i<=size; i++) {
//			System.out.println(Arrays.toString(dp[i]));
//		}
		int ans=n+1;
		for (int i=x; i<size; i++) {
			for (int j=y; j<size; j++) {
				ans=Math.min(ans, dp[i][j]);
			}
		}
		System.out.println(ans);
	}
}
/*
3
5 6
2 1
3 4
2 3

3
8 8
3 4
2 3
2 1
*/
