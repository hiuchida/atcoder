import java.util.*;
public class Main_abc387_d {
	static final boolean DEBUG = false;
	static Scanner sc;
	long ans = Integer.MAX_VALUE;
	Maze mz;
	Point st1, st2, ed;
	void solve() {
		int h = nextInt();
		int w = nextInt();
		nextLine();
		mz = new Maze(h, w);
		mz.initmap();
		mz.loadmap();
		mz.printmap();
//		System.out.println(st);
//		System.out.println(ed);
		
		Deque<Point> que = new ArrayDeque<>();
		que.offer(st1);
		bfs(que);
		mz.printmap();
		
		mz.resetmap();
		que.offer(st2);
		bfs(que);
		mz.printmap();
		
		if (ans == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);
	}
	void bfs(Deque<Point> que) {
		Point p = que.peek();
		mz.setmap(p, 1);
		while (que.size() > 0) {
			p = que.poll();
//			DEBUG(p);
			int val = mz.getmap(p);
			if (p.equals(ed)) {
				ans = Math.min(ans, val-1);
//			} else {
			} else if (ans > val-1) {
				if (p.s.equals("LR")) {
					Point p1 = new Point(p.y-1, p.x, "UD");
					if (mz.checkmap(p1, val+1)) {
						que.offer(p1);
					}
					Point p2 = new Point(p.y+1, p.x, "UD");
					if (mz.checkmap(p2, val+1)) {
						que.offer(p2);
					}
				} else {
					Point p3 = new Point(p.y, p.x-1, "LR");
					if (mz.checkmap(p3, val+1)) {
						que.offer(p3);
					}
					Point p4 = new Point(p.y, p.x+1, "LR");
					if (mz.checkmap(p4, val+1)) {
						que.offer(p4);
					}
				}
			}
		}
	}
	class Maze {
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
						map[y][x] = 0;
					} else if (ch == 'S') {
						map[y][x] = 0;
						st1 = new Point(y, x, "UD");
						st2 = new Point(y, x, "LR");
					} else if (ch == 'G') {
						map[y][x] = 0;
						ed = new Point(y, x, "");
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
		int getmap(Point p) {
			return map[p.y][p.x];
		}
		void setmap(Point p, int val) {
			map[p.y][p.x] = val;
		}
		boolean checkmap(Point p, int val) {
			int v = getmap(p);
			if (v == 0) {
//			if (v == 0 || v > val) {
				setmap(p, val);
				return true;
			}
			return false;
		}
		void printmap() {
			final String tbl = "#.123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
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

	static class Point implements Comparable<Point> {
		final int y;
		final int x;
		final String s;
		Point(int y, int x, String s) {
			this.y = y;
			this.x = x;
			this.s = s;
		}
		@Override
		public int compareTo(Point that) {
			int cmp = Integer.compare(this.y, that.y);
			if (cmp != 0) return cmp;
			return Integer.compare(this.x, that.x);
		}
		@Override
		public int hashCode() {
			return Objects.hash(y, x);
		}
		@Override
		public boolean equals(Object obj) {
			Point that = (Point) obj;
			return this.y == that.y && this.x == that.x;
		}
		@Override
		public String toString() {
			return "(" + y + "," + x + ":" + s + ")";
		}
	}

	//---------------------------------------------------------------
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		new Main_abc387_d().solve();
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
