	//ModFunc20250423
	static final long M=998244353;  //10^9-1755647
	static final long M=1000000000; //10^9
	static final long M=1000000007; //10^9+7
	
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
