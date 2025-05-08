import java.util.*;
public class Main {
	static final long M=998244353;  //10^9-1755647
	static final long M_2=modinv(2, M);
//	static final long M_2=499122177; //(M+1)/2
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		System.out.println(M_2);
		long n = sc.nextLong();
		long ans=calc(n);
		System.out.println(ans);
	}
	static long calc(long x) {
		long ans=0;
		long m=10;
		for (int i=1; i<=18; i++) {
			if (m/10<=x && x<m) {
				ans=modadd(ans, calc(m/10, x));
				break;
			}
			ans=modadd(ans, calc(m/10, m-1));
			m*=10;
		}
		return ans;
	}
	static long calc(long x, long y) {
		long n=y-(x-1);
		long ans=modmul(n, n+1);
		ans=modmul(ans, M_2);
//		long ans=n*(n+1)/2;
//		System.out.println(x+" "+y+" "+n+" "+ans);
		return ans;
	}
	//valをMで割った余り
	static long mod(long val) { //ModFunc20250424
		val%=M;
		if (val<0) val+=M;
		return val;
	}
	//val+xをMで割った余り
	static long modadd(long val, long x) { //ModFunc20250424
		val=mod(val);
		x=mod(x);
		return mod(val+x);
	}
	//val*xをMで割った余り
	static long modmul(long val, long x) { //ModFunc20250424
		val=mod(val);
		x=mod(x);
		return mod(val*x);
	}
	//mod mでのvalの逆元val^{-1}
	static long modinv(long val, long m) { //ModFunc20250424
		long a = val;
		long b = m;
		long u = 1;
		long v = 0;
		while (b!=0) {
			long t = a / b;
			a -= t * b;
			long tmp = a;
			a = b;
			b = tmp;
			u -= t * v;
			tmp = u;
			u = v;
			v = tmp;
		}
		u %= m;
		if (u < 0) u += m;
		return u;
	}
}
/*
16

238

999999999999999999
*/
