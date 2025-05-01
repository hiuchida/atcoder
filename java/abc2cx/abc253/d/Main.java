import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int a = sc.nextInt();
		int b = sc.nextInt();
		long gcd=gcd(a, b);
		long lcm=(long)a*b/gcd;
		int aa=n/a*a;
		int bb=n/b*b;
		long cc=n/lcm*lcm;
//		System.out.println(aa+" "+bb+" "+cc);
		long ans=0;
//		for (int i=1; i<=n; i++) {
//			if (i%a==0) ;
//			else if (i%b==0) ;
//			else ans+=i;
//		}
//		System.out.println(ans);
//		ans=0;
		long sn=calc(1, n);
		long sa=calc(a, aa);
		long sb=calc(b, bb);
		long scc=calc(lcm, cc);
//		System.out.println(sn+" "+sa+" "+sb+" "+scc);
		ans=sn-sa-sb+scc;
		System.out.println(ans);
	}
	static long calc(long a, long b) {
		long n=b/a;
		long ans=n;
		ans*=a+b;
		ans/=2;
		return ans;
	}
	//aとbの最大公約数
	static long gcd(long a, long b) {
		if (a < b) {
			if (a == 0) return b;
			else return gcd(a, b % a);
		}
		if (b == 0) return a;
		else return gcd(b, a % b);
	}
}
/*
10 3 5

1000000000 314 159
*/
/*
100 3 5
*/
