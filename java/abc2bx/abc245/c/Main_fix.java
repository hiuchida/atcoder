import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] aa=new int[n];
		int[] ab=new int[n];
		for (int i=0; i<n; i++) {
			aa[i]=sc.nextInt();
		}
		for (int i=0; i<n; i++) {
			ab[i]=sc.nextInt();
		}
		int[][] dp=new int[2][n];
		dp[0][0]=1;
		dp[1][0]=1;
		for (int i=0; i<n-1; i++) {
			int a0=aa[i];
			int a1=aa[i+1];
			int b0=ab[i];
			int b1=ab[i+1];
			int d00=Math.abs(a0-a1);
			int d01=Math.abs(a0-b1);
			int d10=Math.abs(b0-a1);
			int d11=Math.abs(b0-b1);
			if (dp[0][i]>0) {
				if (d00<=k) dp[0][i+1]++;
				if (d01<=k) dp[1][i+1]++;
			}
			if (dp[1][i]>0) {
				if (d10<=k) dp[0][i+1]++;
				if (d11<=k) dp[1][i+1]++;
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
