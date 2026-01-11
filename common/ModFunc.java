	//ModFunc20250427
	static final long M=998244353;  //10^9-1755647
	static final long M=1000000000; //10^9
	static final long M=1000000007; //10^9+7
	
	static final long M_2=modinv(2, M);
	static final long M_10=modinv(10, M);
	
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
