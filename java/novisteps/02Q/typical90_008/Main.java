import java.util.*;
public class Main {
	static final long M=1000000007; //10^9+7
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		final String t="atcoder";
		int n=sc.nextInt();
		String s=sc.next();
		char[] ary=s.toCharArray();
		long[][] dp=new long[7+1][n+1];
		Arrays.fill(dp[0], 1);
		for (int j=0; j<t.length(); j++) {
			char ch=t.charAt(j);
			for (int i=0; i<n; i++) {
				if (ary[i]==ch) {
					dp[j+1][i+1]=modadd(dp[j+1][i], dp[j][i]);
				} else {
					dp[j+1][i+1]=dp[j+1][i];
				}
			}
		}
//		for (int j=0; j<=7; j++) {
//			System.out.println(Arrays.toString(dp[j]));
//		}
		long ans=dp[7][n];
		System.out.println(ans);
	}
	//valをMで割った余り
	static long mod(long val) { //ModFunc20250427
		val%=M;
		if (val<0) val+=M;
		return val;
	}
	//val+xをMで割った余り
	static long modadd(long val, long x) { //ModFunc20250427
		val=mod(val);
		x=mod(x);
		return mod(val+x);
	}
}
/*
10
attcordeer

41
btwogablwetwoiehocghiewobadegwhoihegnldir

140
aaaaaaaaaaaaaaaaaaaattttttttttttttttttttccccccccccccccccccccooooooooooooooooooooddddddddddddddddddddeeeeeeeeeeeeeeeeeeeerrrrrrrrrrrrrrrrrrrr
*/
