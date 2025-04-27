import java.util.*;
public class Main {
	static final long M=998244353;  //10^9-1755647
	public static void main(String[] args) {
		long inv=modinv(2, M);
		System.out.println(inv); //499122177
		long a=12345678900000L;
		long b=100000;
		long inv2=mod(mod(a)*modinv(b, M));
		System.out.println(inv2); //123456789
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
	//val*xをMで割った余り
	static long modmul(long val, long x) { //ModFunc20250427
		val=mod(val);
		x=mod(x);
		return mod(val*x);
	}
	//mod mでのvalの逆元val^{-1}
	static long modinv(long val, long m) { //ModFunc20250427
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
	//a^n mod mを計算する
	static long modpow(long a, long n, long m) { //ModFunc20250427
		long ans = 1;
		while (n > 0) {
			if ((n & 1) > 0) ans = ans * a % m;
			a = a * a % m;
			n >>= 1;
		}
		return ans;
	}
	
	//----------------------------------------------------------------------------
	//a^{-1} mod mを計算する（フェルマーの小定理）
	static long modinv2(long a, long m) {
		return modpow(a, m - 2, m);
	}
	
	static long fac[];
	static long finv[];
	static long inv[];
	//テーブルを作る前処理
	static void COMinit(int max) {
		fac = new long[max];
		finv = new long[max];
		inv = new long[max];
	    fac[0] = fac[1] = 1;
	    finv[0] = finv[1] = 1;
	    inv[1] = 1;
	    for (int i = 2; i < max; i++) {
	        fac[i] = fac[i - 1] * i % M;
	        inv[i] = M - inv[(int)(M % i)] * (M / i) % M;
	        finv[i] = finv[i - 1] * inv[i] % M;
	    }
	}
	//二項係数計算
	static long COM(int n, int k) {
	    if (n < k) return 0;
	    if (n < 0 || k < 0) return 0;
	    return fac[n] * (finv[k] * finv[n - k] % M) % M;
	}
}
/*
*/
