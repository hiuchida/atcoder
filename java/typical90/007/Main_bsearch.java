import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static Scanner sc;
	long ans = 0;
	void solve() {
		int n = nextInt();
		int[] ary = nextIntAry(n);
		Arrays.sort(ary);
		DEBUG(ary);
		int q = nextInt();
		for (int i=0; i<q; i++) {
			int b = nextInt();
			int l = Arrays.binarySearch(ary, b);
			if (l<0) {
				l=-(l+1)-1;
			}
			int bl = Integer.MAX_VALUE;
			int br = Integer.MAX_VALUE;
			if (l>=0) bl=Math.abs(b-ary[l]);
			else DEBUG("skip:" + l);
			l++;
			if (l<n) br=Math.abs(b-ary[l]);
			else DEBUG("skip:" + l);
			int min = Math.min(bl, br);
			System.out.println(min);
		}
	}
/*
4
100 200 300 400
3
90
120
430
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
