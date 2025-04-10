import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		long k=sc.nextLong();
		long[] dp=new long[30];
		dp[1]=1;
		for (int i=2; i<dp.length; i++) {
			dp[i]=dp[i-1]*i;
//			System.out.println(i);
			if (dp[i]<0) break;
		}
//		System.out.println(Arrays.toString(dp));
		for (int i=2; i<dp.length; i++) {
			long x=dp[i];
			if (x%k==0) {
				System.out.println(i);
				System.exit(0);
			}
		}
	}
}
/*
30

123456789011

280
*/
