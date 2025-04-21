import java.util.*;
public class Main {
	static final long M=998244353;  //10^9-1755647
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long[][] ary=new long[n+1][10];
		for (int i=1; i<=9; i++) {
			ary[1][i]=1;
		}
		for (int i=1; i<n; i++) {
			calc(ary, i);
		}
//		for (int i=1; i<=n; i++) {
//			System.out.println(Arrays.toString(ary[i]));
//		}
		long ans=sum(ary[n]);
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
	static long sum(long[] ary) {
		long ans=0;
		for (int i=1; i<=9; i++) {
			ans=modadd(ans, ary[i]);
		}
		return ans;
	}
	static void calc(long[][] ary, int j) {
		for (int i=1; i<=9; i++) {
			if (i==1) {
				ary[j+1][i+1]=modadd(ary[j+1][i+1], ary[j][i]);
			} else if (i==9) {
				ary[j+1][i-1]=modadd(ary[j+1][i-1], ary[j][i]);
			} else {
				ary[j+1][i-1]=modadd(ary[j+1][i-1], ary[j][i]);
				ary[j+1][i+1]=modadd(ary[j+1][i+1], ary[j][i]);
			}
			ary[j+1][i]=modadd(ary[j+1][i], ary[j][i]);
		}
	}
}
/*
4

2

1000000
*/
