import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int k=sc.nextInt();
		int[] ary=new int[n+1];
		for (int i=1; i<=n; i++) {
			ary[i]=sc.nextInt();
		}
		int[] dp=new int[n+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[1]=0;
		for (int i=1; i<n; i++) {
			for (int j=i+1; j<=i+k; j++) {
				if (j<=n) {
					dp[j]=Math.min(dp[j], dp[i]+Math.abs(ary[j]-ary[i]));
				}
			}
		}
//		System.out.println(Arrays.toString(dp));
		int ans=dp[n];
		System.out.println(ans);
	}
}
/*
5 3
10 30 40 50 20

3 1
10 20 10

2 100
10 10

10 4
40 10 20 70 80 10 20 70 80 60
*/
