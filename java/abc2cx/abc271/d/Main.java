import java.util.*;
public class Main {
	static int n;
	static int[][] dp;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		int s = sc.nextInt();
		int[] aa=new int[n];
		int[] ab=new int[n];
		for (int i=0; i<n; i++) {
			aa[i]=sc.nextInt();
			ab[i]=sc.nextInt();
		}
//		System.out.println(Arrays.toString(aa));
//		System.out.println(Arrays.toString(ab));
		dp=new int[n+1][s+1];
		dp[0][0]=1;
		for (int i=0; i<n; i++) {
			for (int j=0; j<s; j++) {
				if (dp[i][j]==0) continue;
				int a=aa[i];
				if (j+a<=s) {
					dp[i+1][j+a]=1;
				}
				int b=ab[i];
				if (j+b<=s) {
					dp[i+1][j+b]=2;
				}
			}
		}
//		print();
		if (dp[n][s]==0) {
			System.out.println("No");
			System.exit(0);
		}
		System.out.println("Yes");
		char[] chs=new char[n];
		int j=s;
		for (int i=n; i>0; i--) {
			boolean bh=dp[i][j]==1;
			if (bh) {
				chs[i-1]='H';
				j-=aa[i-1];
			} else {
				chs[i-1]='T';
				j-=ab[i-1];
			}
		}
		String ans=new String(chs);
		System.out.println(ans);
	}
	static void print() {
		for (int i=0; i<=n; i++) {
			System.out.println(Arrays.toString(dp[i]));
		}
	}
}
/*
3 11
1 4
2 3
5 7

5 25
2 8
9 3
4 11
5 1
12 6
*/
