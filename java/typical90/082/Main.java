import java.util.*;
public class Main {
	static final long M=1000000007; //10^9+7
	static final long M_2=modinv(2, M);
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
//		System.out.println(M_2);
		long l=sc.nextLong();
		long r=sc.nextLong();
		long ar=calc(r);
		long al=calc(l-1);
//		System.out.println(al+" "+ar);
		long ans=modadd(ar, -al);
		System.out.println(ans);
	}
	static long calc(long x) {
		final long N=(long)1e18;
		long ans=0;
		if (x==0) return 0;
		if (x==N) {
			ans+=modmul(19, N);
			x--;
		}
		long m=10;
		for (int i=1; i<=18; i++) {
			if (m/10<=x && x<m) {
				ans=modadd(ans, calc(m/10, x, i));
				break;
			}
			ans=modadd(ans, calc(m/10, m-1, i));
			m*=10;
		}
		return ans;
	}
	static long calc(long x, long y, long z) {
		long ans=modmul(z, x+y);
		ans=modmul(ans, y-x+1);
		ans=modmul(ans, M_2);
//		long ans=z*(x+y)*(y-x+1)/2;
//		System.out.println(x+" "+y+" "+z+" "+ans);
		return ans;
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
3 5

98 100

1001 869120

381453331666495446 746254773042091083
*/
/*
1 1000000000000000000

999999999999999999 1000000000000000000

1000000000000000000 1000000000000000000
*/
