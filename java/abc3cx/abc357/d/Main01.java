import java.util.*;
public class Main {
	static final long M=998244353;  //10^9-1755647
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		long n=sc.nextLong();
		long w0=calc(n);
		long w=w0;
		long ans=n;
		for (long i=1; i<n; i++) {
//			long a=n*w;
//			w*=w0;
//			ans+=a;
			long a=modmul(n, w);
			w=modmul(w, w0);
			ans=modadd(ans, a);
//			System.out.println(i+" "+ans);
		}
		System.out.println(ans);
	}
	static long calc(long x) { //abc357_d: x<10^kを満たす最小の10^k
		long v=1;
		while (v<=x) v*=10;
		return v;
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
	//val*xをMで割った余り
	static long modmul(long val, long x) { //ModFunc20250423
		val=mod(val);
		x=mod(x);
		return mod(val*x);
	}
}
/*
5

9

10000000000
*/
