import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int x=sc.nextInt();
		int y=sc.nextInt();
		int z=sc.nextInt();
		String s=sc.next();
		char[] ary=s.toCharArray();
		int n=ary.length;
		long[][] dp=new long[n+1][2];
		for (int i=0; i<=n; i++) {
			Arrays.fill(dp[i], Long.MAX_VALUE);
		}
		dp[0][0]=0;
		dp[0][1]=Integer.MAX_VALUE;
		for (int i=0; i<n; i++) {
			if (ary[i]=='a') {
				dp[i+1][0]=Math.min(dp[i+1][0], dp[i][0]+x);
				dp[i+1][1]=Math.min(dp[i+1][1], dp[i][0]+y+z);
				dp[i+1][0]=Math.min(dp[i+1][0], dp[i][1]+x+z);
				dp[i+1][1]=Math.min(dp[i+1][1], dp[i][1]+y);
			} else {
				dp[i+1][0]=Math.min(dp[i+1][0], dp[i][0]+y);
				dp[i+1][1]=Math.min(dp[i+1][1], dp[i][0]+x+z);
				dp[i+1][0]=Math.min(dp[i+1][0], dp[i][1]+y+z);
				dp[i+1][1]=Math.min(dp[i+1][1], dp[i][1]+x);
			}
		}
//		for (int i=0; i<=n; i++) {
//			System.out.println(Arrays.toString(dp[i]));
//		}
		long ans=Math.min(dp[n][0], dp[n][1]);
		System.out.println(ans);
	}
}
/*
1 3 3
AAaA

1 1 100
aAaAaA

1 2 4
aaAaAaaAAAAaAaaAaAAaaaAAAAA
*/
