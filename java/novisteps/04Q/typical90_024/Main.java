import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static Scanner sc;
	long ans = 0;
	void solve() {
		int n = nextInt();
		int k = nextInt();
		int[] aa = nextIntAry(n);
		int[] ab = nextIntAry(n);
		int[] ad = new int[n];
		for (int i=0; i<n; i++) {
			ad[i] = ab[i]-aa[i];
		}
		DEBUG(aa);
		DEBUG(ab);
		DEBUG(ad);
		long ans = 0;
		for (int i=0; i<n; i++) {
			ans += Math.abs(ad[i]);
		}
		DEBUG(ans);
		if (ans > k) ng();
		k -= ans;
		if (k%2==0) ok();
		ng();
	}


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
