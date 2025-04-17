import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		long k=sc.nextLong();
		final int m=60; //2^60=1,152,921,504,606,846,976
		int[][] dp=new int[m+1][n];
		for (int i=0; i<n; i++) {
			dp[0][i]=sc.nextInt()-1;
		}
		for (int j=1; j<=m; j++) {
			for (int i=0; i<n; i++) {
				dp[j][i]=dp[j-1][dp[j-1][i]];
			}
		}
//		for (int j=0; j<=m; j++) {
//			System.out.println(Arrays.toString(dp[j]));
//		}
		int ans=0;
		int b=0;
		while (k>0) {
			if ((k&1)>0) {
				ans=dp[b][ans];
			}
			k/=2;
			b++;
		}
		ans++;
		System.out.println(ans);
	}
}
/*
4 5
3 2 4 1

6 727202214173249351
6 5 2 5 3 2
*/
