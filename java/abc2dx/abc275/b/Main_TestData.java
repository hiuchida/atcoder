import java.util.*;
public class Main {
	static final long M=998244353;  //10^9-1755647
	public static void main(String[] args) {
		for (long a=0; a<=(long)1e18; a++) {
			long b=a;
			long c=a;
			for (long d=0; d<=a; d++) {
				long e=d;
				long f=d;
				long a1=main1(a, b, c, d, e, f);
				long a0=main0(a, b, c, d, e, f);
				if (a1!=a0) {
					System.out.println(a+" "+d+" "+a1+" "+a0);
					System.exit(0);
				}
			}
		}
	}
	public static long main1(long a, long b, long c, long d, long e, long f) {
		Scanner sc=new Scanner(System.in);
//		long a=sc.nextLong();
//		long b=sc.nextLong();
//		long c=sc.nextLong();
//		long d=sc.nextLong();
//		long e=sc.nextLong();
//		long f=sc.nextLong();
		a%=M;
		b%=M;
		c%=M;
		d%=M;
		e%=M;
		f%=M;
		long ab=a*b;
		ab%=M;
		long abc=ab*c;
		abc%=M;
		long de=d*e;
		de%=M;
		long def=de*f;
		def%=M;
		long ans=abc-def;
		while (ans<0) ans+=M;
		ans%=M;
//		System.out.println(ans);
		return ans;
	}
	public static long main0(long a, long b, long c, long d, long e, long f) {
		Scanner sc=new Scanner(System.in);
//		long a=sc.nextLong();
//		long b=sc.nextLong();
//		long c=sc.nextLong();
//		long d=sc.nextLong();
//		long e=sc.nextLong();
//		long f=sc.nextLong();
		a%=M;
		b%=M;
		c%=M;
		d%=M;
		e%=M;
		f%=M;
		long ab=a*b;
		ab%=M;
		long abc=ab*c;
		abc%=M;
		long de=d*e;
		de%=M;
		long def=de*f;
		def%=M;
		long ans=abc-def;
		ans%=M;
//		System.out.println(ans);
		return ans;
	}
}
/*
1000 121 998228439 -15914
*/
