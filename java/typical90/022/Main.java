import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static void DEBUG(Object x) {
		if (DEBUG) System.out.println(x);
	}
	static void DEBUG(long x) {
		if (DEBUG) DEBUG(""+x);
    }
	static void DEBUG(int[] x) {
		if (DEBUG) DEBUG(Arrays.toString(x));
    }
	static long gcd(long a, long b) {
		if (a < b) {
			if (a == 0) return b;
			else return gcd(a, b % a);
		}
		if (b == 0) return a;
		else return gcd(b, a % b);
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long a = sc.nextLong();
		long b = sc.nextLong();
		long c = sc.nextLong();
		long gab = gcd(a, b);
		long gabc = gcd(gab, c);
		DEBUG(gab);
		DEBUG(gabc);
		long ans = (a/gabc-1)+(b/gabc-1)+(c/gabc-1);
		System.out.println(ans);
	}
}
