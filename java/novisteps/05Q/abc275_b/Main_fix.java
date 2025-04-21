import java.util.*;
public class Main {
	static final long M=998244353;  //10^9-1755647
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		long a=sc.nextLong();
		long b=sc.nextLong();
		long c=sc.nextLong();
		long d=sc.nextLong();
		long e=sc.nextLong();
		long f=sc.nextLong();
//		a%=M;
//		b%=M;
//		c%=M;
//		d%=M;
//		e%=M;
//		f%=M;
//		long ab=a*b;
//		ab%=M;
//		long abc=ab*c;
//		abc%=M;
//		long de=d*e;
//		de%=M;
//		long def=de*f;
//		def%=M;
//		long ans=abc-def;
//		while (ans<0) ans+=M;
//		ans%=M;
		long ab=modmul(a, b);
		long abc=modmul(ab, c);
		long de=modmul(d, e);
		long def=modmul(de, f);
		long ans=modadd(abc, -def);
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
	//val*xをMで割った余り
	static long modmul(long val, long x) { //ModFunc20250423
		val=mod(val);
		x=mod(x);
		return mod(val*x);
	}
}
/*
2 3 5 1 2 4

1 1 1000000000 0 0 0

1000000000000000000 1000000000000000000 1000000000000000000 1000000000000000000 1000000000000000000 1000000000000000000
*/
/*
1000 1000 1000 121 121 121
↓ok
998228439
*/
