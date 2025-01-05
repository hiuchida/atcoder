import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static Scanner sc;
	long ans = 0;
	void solve() {
		int n = nextInt();
		int[] ary = nextIntAry(n);
//		Arrays.sort(ary);
//		DEBUG(ary);
		TreeSet<Integer> set = new TreeSet<>();
		for (int i=0; i<n; i++) set.add(ary[i]);
		DEBUG(set);
		int q = nextInt();
		for (int i=0; i<q; i++) {
			int b = nextInt();
			Integer l = set.floor(b);
			Integer r = set.ceiling(b);
			int bl = Integer.MAX_VALUE;
			int br = Integer.MAX_VALUE;
			if (l != null) bl=Math.abs(b-l);
			if (r != null) br=Math.abs(b-r);
			int min = Math.min(bl, br);
			System.out.println(min);
		}
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
