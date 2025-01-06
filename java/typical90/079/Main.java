import java.util.*;
public class Main {
	static final boolean DEBUG = true;
	static Scanner sc;
	long ans = 0;
	void solve() {
		int h = nextInt();
		int w = nextInt();
		int[][] aa = new int[h][w];
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				aa[y][x] = nextInt();
			}
		}
		int[][] ab = new int[h][w];
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				ab[y][x] = nextInt();
			}
		}
//		for (int y = 0; y < h; y++) {
//			DEBUG(aa[y]);
//		}
//		DEBUG("");
//		for (int y = 0; y < h; y++) {
//			DEBUG(ab[y]);
//		}
//		DEBUG("");
		for (int y = 0; y < h-1; y++) {
			for (int x = 0; x < w-1; x++) {
				int d = ab[y][x] - aa[y][x];
				aa[y][x] += d;
				aa[y+1][x] += d;
				aa[y][x+1] += d;
				aa[y+1][x+1] += d;
				ans += Math.abs(d);
			}
		}
//		for (int y = 0; y < h; y++) {
//			DEBUG(aa[y]);
//		}
//		DEBUG("");
//		for (int y = 0; y < h; y++) {
//			DEBUG(ab[y]);
//		}
//		DEBUG("");
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				if (aa[y][x] != ab[y][x]) ng();
			}
		}
		System.out.println("Yes");
		System.out.println(ans);
	}
/*
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
