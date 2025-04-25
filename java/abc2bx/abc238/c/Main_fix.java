import java.util.*;
public class Main {
	static final long M=998244353;  //10^9-1755647
	static final long M_2=modinv(2, M);
//	static final long M_2=499122177; //(M+1)/2
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		System.out.println(M_2);
		long n = sc.nextLong();
		long ans=0;
		ans=calc(n);
		System.out.println(ans);
	}
	static long calc(long n) {
		long w=10;
		while (true) {
			if (n<w) {
				return calc(n, w);
			}
			w*=10;
		}
	}
	static long calc(long n, long w) {
		long ans=0;
		w/=10;
		if (w>1) {
			ans=calc(w-1, w);
//			System.out.println(w+" "+ans);
		}
		n-=w-1;
		long nn=modmul(n, n+1);
		long nn2=modmul(nn, M_2);
		ans=modadd(ans, nn2);
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
