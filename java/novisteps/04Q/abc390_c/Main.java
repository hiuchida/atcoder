import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static Scanner sc;
	long ans = 0;
	void solve() {
		int h = nextInt();
		int w = nextInt();
		nextLine();
		Maze mz;
		mz = new Maze(h, w);
		mz.initmap();
		mz.loadmap();
		mz.printmap();
		int lt=9999, tp=9999, rt=0, bm=0;
		for (int y = 1; y <= h; y++) {
			for (int x = 1; x <= w; x++) {
				if (mz.map[y][x]==2) {
					lt=Math.min(lt, x);
					tp=Math.min(tp, y);
					rt=Math.max(rt, x);
					bm=Math.max(bm, y);
				}
			}
		}
		DEBUG(lt+" "+tp+ " "+rt+" "+bm);
		for (int y = tp; y <= bm; y++) {
			for (int x = lt; x <= rt; x++) {
				if (mz.map[y][x]==1) {
					System.out.println("No");
					System.exit(0);
				}
			}
		}
		System.out.println("Yes");
	}
	static class Maze {
		int h;
		int w;
		int[][] map; //map of Maze
		Maze(int h, int w) {
			this.h = h;
			this.w = w;
			this.map = new int[h + 2][w + 2];
		}
		void initmap() {
			for (int y = 0; y < h + 2; y++) {
				for (int x = 0; x < w + 2; x++) {
					map[y][x] = -1;
				}
			}
		}
		void loadmap() {
			for (int y = 1; y <= h; y++) {
				String s = nextLine();
				for (int x = 1; x <= w; x++) {
					char ch = s.charAt(x - 1);
					if (ch == '.') {
						map[y][x] = 1;
					} else if (ch == '#') {
						map[y][x] = 2;
					} else if (ch == '?') {
						map[y][x] = 0;
					}
				}
			}
		}
		void resetmap() {
			for (int y = 0; y < h + 2; y++) {
				for (int x = 0; x < w + 2; x++) {
					if (map[y][x] > 0) map[y][x] = 0;
				}
			}
		}
		void printmap() {
			final String tbl = " ?.#123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
			if (DEBUG) {
				for (int y = 0; y < h + 2; y++) {
					for (int x = 0; x < w + 2; x++) {
						int i=map[y][x]+1;
						while (i>=tbl.length()) i-=61;
						if (i<tbl.length()) System.out.print(tbl.charAt(i));
						else System.out.print('@');
					}
					System.out.println();
				}
				System.out.println();
			}
		}
	}
/*
3 5
.#?#.
.?#?.
?...?

3 3
?##
#.#
##?

1 1
#
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
	static String nextLine() {
		return sc.nextLine();
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
