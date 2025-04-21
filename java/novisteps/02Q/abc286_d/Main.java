import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int x=sc.nextInt();
		int[] aa=new int[n];
		int[] ab=new int[n];
		for (int i=0; i<n; i++) {
			aa[i]=sc.nextInt();
			ab[i]=sc.nextInt();
		}
//		System.out.println(Arrays.toString(aa));
//		System.out.println(Arrays.toString(ab));
		int[][] dp=new int[n][x+1];
		for (int k=0; k<=ab[0]; k++) {
			if (k*aa[0]<=x) {
				dp[0][k*aa[0]]=1;
			}
		}
		for (int j=0; j<x; j++) {
			for (int i=1; i<n; i++) {
				if (dp[i-1][j]>0) {
					for (int k=0; k<=ab[i]; k++) {
						if (j+k*aa[i]<=x) {
							dp[i][j+k*aa[i]]=1;
						}
					}					
				}
			}
		}
//		for (int i=0; i<n; i++) {
//			System.out.println(Arrays.toString(dp[i]));
//		}
		for (int i=0; i<n; i++) {
			if (dp[i][x]>0) {
				System.out.println("Yes");
				System.exit(0);
			}
		}
		System.out.println("No");
	}
}
/*
2 19
2 3
5 6

2 18
2 3
5 6

3 1001
1 1
2 1
100 10
*/
