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
		System.out.println(ans);
	}
}
/*
2 3 5 1 2 4

1 1 1000000000 0 0 0

1000000000000000000 1000000000000000000 1000000000000000000 1000000000000000000 1000000000000000000 1000000000000000000
*/
/*
1000 1000 1000 121 121 121
↓ng
-15914
*/
