import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static Scanner sc;
	int n;
	long ans = 0;
	void solve() {
		n = nextInt();
		for (int i=0; i<1 << n; i++) {
			StringBuilder sb = new StringBuilder();
			int kakko = 0;
			for (int j=n-1; j>=0; j--) {
				int mask = 1 << j;
				if ((i&mask) > 0) {
					sb.append(")");
					kakko--;
				} else {
					sb.append("(");
					kakko++;
				}
				if (kakko < 0) break;
			}
			if (kakko == 0) System.out.println(sb);
//			else DEBUG(sb + " " + kakko);
		}
	}

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
		return next();
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
