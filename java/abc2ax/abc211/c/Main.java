import java.util.*;
public class Main {
	static final long M=1000000007; //10^9+7
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		final String t="chokudai";
		String s=sc.next();
		int n=s.length();
		char[] ary=s.toCharArray();
		long[][] dp=new long[8+1][n+1];
		for (int i=0; i<=n; i++) {
			dp[0][i]=1;
		}
		for (int j=0; j<t.length(); j++) {
			char ch=t.charAt(j);
			for (int i=0; i<n; i++) {
				if (s.charAt(i)==ch) {
					dp[j+1][i+1]=modadd(dp[j+1][i], dp[j][i]);
				} else {
					dp[j+1][i+1]=dp[j+1][i];
				}
			}
		}
//		for (int j=0; j<=t.length(); j++) {
//			System.out.println(Arrays.toString(dp[j]));
//		}
		long ans=dp[8][n];
		System.out.println(ans);
	}
	//valをMで割った余り
	static long mod(long val) { //ModFunc20250421
		return val%M;
	}
	//val+xをMで割った余り
	static long modadd(long val, long x) { //ModFunc20250421
		return mod(val+x);
	}
}
/*
chchokudai

atcoderrr

chokudaichokudaichokudai
*/
/*
iadukohc
*/
