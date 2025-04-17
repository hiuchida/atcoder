import java.util.*;
public class Main {
	static final long M=998244353;  //10^9-1755647
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] aa=new int[n];
		int[] ab=new int[n];
		for (int i=0; i<n; i++) {
			aa[i]=sc.nextInt();
			ab[i]=sc.nextInt();
		}
		long[][] dp=new long[n][2];
		dp[0][0]=1;
		dp[0][1]=1;
		for (int i=0; i<n-1; i++) {
			if (aa[i]!=aa[i+1]) dp[i+1][0]=modadd(dp[i+1][0], dp[i][0]);
			if (aa[i]!=ab[i+1]) dp[i+1][1]=modadd(dp[i+1][1], dp[i][0]);
			if (ab[i]!=aa[i+1]) dp[i+1][0]=modadd(dp[i+1][0], dp[i][1]);
			if (ab[i]!=ab[i+1]) dp[i+1][1]=modadd(dp[i+1][1], dp[i][1]);
		}
//		for (int i=0; i<n; i++) {
//			System.out.println(Arrays.toString(dp[i]));
//		}
		long ans=modadd(dp[n-1][0], dp[n-1][1]);
		System.out.println(ans);
	}
	static long mod(long val) { //abc065_c,abc211_c,abc211_d,abc291_d: valをMで割った余り
		return val%M;
	}
	static long modadd(long val, long x) { //abc211_c,abc211_d,abc291_d: val+xをMで割った余り
		return mod(val+x);
	}
}
/*
3
1 2
4 2
3 4

4
1 5
2 6
3 7
4 8

8
877914575 602436426
861648772 623690081
476190629 262703497
971407775 628894325
822804784 450968417
161735902 822804784
161735902 822804784
822804784 161735902
*/
