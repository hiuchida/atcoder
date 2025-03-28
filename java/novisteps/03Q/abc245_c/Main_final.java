import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[][] ary=new int[2][n];
		for (int i=0; i<n; i++) {
			ary[0][i]=sc.nextInt();
		}
		for (int i=0; i<n; i++) {
			ary[1][i]=sc.nextInt();
		}
		int[][] dp=new int[2][n];
		dp[0][0]=1;
		dp[1][0]=1;
		for (int i=0; i<n-1; i++) {
			for (int from=0; from<2; from++) {
				for (int to=0; to<2; to++) {
					if (dp[from][i]>0) {
						if (Math.abs(ary[from][i]-ary[to][i+1])<=k) dp[to][i+1]++;
					}
				}
			}
		}
//		System.out.println(Arrays.toString(dp[0]));
//		System.out.println(Arrays.toString(dp[1]));
		if (dp[0][n-1]==0 && dp[1][n-1]==0) {
			System.out.println("No");
			System.exit(0);
		}
		System.out.println("Yes");
	}
}
/*
5 4
9 8 3 7 2
1 6 2 9 5

4 90
1 1 1 100
1 2 3 100


4 1000000000
1 1 1000000000 1000000000
1 1000000000 1 1000000000
*/
