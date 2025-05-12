import java.util.*;
public class Main {
	static final long M=998244353;  //10^9-1755647
	static final int N=(int)1e6;
	static long[] fact=new long[3*N+1];
	static void init() {
		fact[1]=1;
		for (int i=2; i<=N; i++) {
			fact[i]=modmul(fact[i-1], i);
		}
	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		init();
		//a<c
		//a<d
		//b<d
		
		//1111
		//abcd
		//abdc
		//acbd:1
		//bacd
		//badc
		int a=sc.nextInt();
		int b=sc.nextInt();
		int c=sc.nextInt();
		int d=sc.nextInt();
		long ans=0;
		int bcd=b+c+d;
		int cd=c+d;
		long fbcd=fact[bcd];
		long fa=fact[a];
		long fb=fact[b];
		long fcd=fact[cd];
		long fc=fact[c];
		long fd=fact[d];
		long x=fbcd/fb/fc/fd;
		long y=fcd/fc/fd;
		long z=x+y;
//		long x=modmul(fbcd, modinv(fb, M));
//		x=modmul(x, modinv(fc, M));
//		x=modmul(x, modinv(fd, M));
//		long y=modmul(fcd, modinv(fc, M));
//		y=modmul(y, modinv(fd, M));
//		long z=modadd(x, y);
		System.out.println(fbcd+" "+fa+" "+fb+" "+fc+" "+x);
		System.out.println(fcd+" "+fc+" "+fd+" "+y);
		System.out.println(z);
		ans=modadd(ans, z);
		System.out.println(ans);
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
}
/*
1 1 1 1

1 2 4 8

834150 21994 467364 994225
*/
