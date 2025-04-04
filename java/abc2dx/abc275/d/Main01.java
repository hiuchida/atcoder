import java.util.*;
public class Main {
	static long dp[];
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		dp=new long[1000*1000];
		long n=sc.nextLong();
		long ans=calc(n);
		System.out.println(ans);
//		for (long n=0; n<20; n++) {
//			long ans=calc(n);
//			System.out.println(n+" "+ans);
//		}
	}
	static long calc(long n) {
		if (n==0) return 1;
		int nn=0;
		if (n<dp.length) {
			nn=(int)n;
			if (dp[nn]>0) return dp[nn];
		}
		long v1=calc(n/2);
		long v2=calc(n/3);
		if (n<dp.length) {
			dp[nn]=v1+v2;
		}
		return v1+v2;
	}
}
/*
2

0

100
*/
