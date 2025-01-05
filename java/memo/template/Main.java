import java.util.*;
public class Main {
	static final boolean DEBUG = true;
	static Scanner sc;
	long ans = 0;
	void solve() {
		int n = nextInt();
		long l = nextLong();
		String s = next();
		DEBUG(n + " " + l + " " + s);
		int[] ary = nextIntAry(n);
		Arrays.sort(ary);
		DEBUG(ary);
		for (int i=0; i<n; i++) {
			ans += ary[i];
		}
		int[] sum = new int[n+1];
		for (int i=0; i<n; i++) {
			sum[i+1] = sum[i] + ary[i];
		}
		DEBUG(sum);
		System.out.println(ans);
	}
/*
3 1234 abc
4 7 3
*/

	//---------------------------------------------------------------
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		new Main().solve();
	}
	static int nextInt() {
		return sc.nextInt();
	}
	static long nextLong() {
		return sc.nextLong();
	}
	static String next() {
		return sc.next();
	}
	static int[] nextIntAry(int n) {
		int[] ary = new int[n];
		for (int i=0; i<n; i++) {
			int a = nextInt();
			ary[i] = a;
		}
		return ary;
	}
	static void DEBUG(Object x) {
		if (DEBUG) System.out.println(x);
	}
	static void DEBUG(long x) {
		if (DEBUG) DEBUG(""+x);
    }
	static void DEBUG(int[] x) {
		if (DEBUG) DEBUG(Arrays.toString(x));
    }
	static void ok() {
		System.out.println("Yes");
		System.exit(0);
	}
	static void ng() {
		System.out.println("No");
		System.exit(0);
	}
}
