import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		long n=sc.nextLong();
		long m=sc.nextLong();
		long p=sc.nextLong();
		long ans=modpow(n, p, m);
		System.out.println(ans);
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
12 15 7

123456789 234567894 6574837563712
*/
