import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		final int M=(int)1e5;
		final int m=60; //2^60=1,152,921,504,606,846,976
		int n=sc.nextInt();
		long k=sc.nextLong();
		int[][] dp=new int[m+1][M];
		for (int i=0; i<M; i++) {
			dp[0][i]=(i+calc(i))%M;
		}
//		System.out.println(Arrays.toString(dp[0]));
		for (int j=1; j<=m; j++) {
			for (int i=0; i<M; i++) {
				dp[j][i]=dp[j-1][dp[j-1][i]];
			}
		}
//		for (int j=1; j<=m; j++) {
//			System.out.println(Arrays.toString(dp[j]));
//		}
		int j=0;
		while (k>0) {
			if ((k&1)>0) {
				n=dp[j][n];
			}
			j++;
			k/=2;
		}
		System.out.println(n);
	}
	//abc080_b,abc083_b,abc101_b,abc187_a: nを十進法で表したときの各桁の和
	static int calc(int n) {
		int ans=0;
		while (n>0) {
			ans+=n%10;
			n/=10;
		}
		return ans;
	}
}
/*
5 3

0 100

99999 1000000000000000000
*/
