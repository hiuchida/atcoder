import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int s=sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		boolean[][] dp=new boolean[n+1][s+1];
		dp[0][0]=true;
		for (int i=0; i<n; i++) {
			for (int j=0; j<s; j++) {
				if (!dp[i][j]) continue;
				dp[i+1][j]=true;
				if (j+ary[i]<=s) dp[i+1][j+ary[i]]=true;
			}
		}
//		for (int i=0; i<=n; i++) {
//			System.out.println(Arrays.toString(dp[i]));
//		}
		if (dp[n][s]) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
3 11
2 5 9

4 11
3 1 4 5
*/
