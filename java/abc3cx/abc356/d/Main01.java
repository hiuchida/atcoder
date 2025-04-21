import java.util.*;
public class Main {
	static final long M=998244353;  //10^9-1755647
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		long n=sc.nextLong();
		long m=sc.nextLong();
		long ans=0;
		for (long i=0; i<=n; i++) {
			long x=i&m;
			int c=Long.bitCount(x);
			ans=modadd(ans, c);
		}
		System.out.println(ans);
	}
	//valをMで割った余り
	static long mod(long val) { //ModFunc20250423
		val%=M;
		if (val<0) val+=M;
		return val;
	}
	//val+xをMで割った余り
	static long modadd(long val, long x) { //ModFunc20250423
		val=mod(val);
		x=mod(x);
		return mod(val+x);
	}
}
/*
4 3

0 0

1152921504606846975 1152921504606846975
*/
