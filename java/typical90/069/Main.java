import java.util.*;
public class Main {
	static final long M=1000000007; //10^9+7
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		long n=sc.nextLong();
		long k=sc.nextInt();
		long ans=0;
		if (n==1) {
			ans=k;
		} else if (n==2) {
			ans=modmul(k, k-1);
		} else if (k==2) {
			ans=0;
		} else {
			ans=modmul(k, k-1);
//			for (int i=0; i<n-2; i++) ans=modmul(ans, k-2);
			long a2=modpow(k-2, n-2, M);
			ans=modmul(ans, a2);
		}
		System.out.println(ans);
	}
	//valをMで割った余り
	static long mod(long val) { //ModFunc20250427
		val%=M;
		if (val<0) val+=M;
		return val;
	}
	//val*xをMで割った余り
	static long modmul(long val, long x) { //ModFunc20250427
		val=mod(val);
		x=mod(x);
		return mod(val*x);
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
}
/*
2 3

10 2

2021 617
*/
/*
1 1

1 2

2 1

2 2

3 2

2 1000000000
*/
