import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		int n=s.length();
		char[] ary=s.toCharArray();
		final int m=18; //2^18=262,144
		int[][] dp=new int[m+1][n];
		for (int i=0; i<n; i++) {
			if (ary[i]=='L') dp[0][i]=i-1;
			else dp[0][i]=i+1;
		}
		for (int j=1; j<=m; j++) {
			for (int i=0; i<n; i++) {
				dp[j][i]=dp[j-1][dp[j-1][i]];
			}
		}
//		for (int j=0; j<=m; j++) {
//			System.out.println(Arrays.toString(dp[j]));
//		}
		int[] ans=new int[n];
		for (int i=0; i<n; i++) {
			ans[dp[m][i]]++;
		}
		for (int i=0; i<n; i++) {
			System.out.print(ans[i]+" ");
		}
		System.out.println();
	}
}
/*
RRLRL

RRLLLLRLRRLL

RRRLLRLLRRRLLLLL
*/
/*
RRRRRRRRLLLLLLLL
*/
