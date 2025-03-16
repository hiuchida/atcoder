import java.util.*;
public class Main {
	static final boolean DEBUG = true;
	static Scanner sc;
	long ans = 0;
	void solve() {
		int n = nextInt();
		nextLine();
		Maze mz1;
		mz1 = new Maze(n, n);
		Maze mz2;
		mz2 = new Maze(n, n);
		mz1.initmap();
		mz2.initmap();
		mz1.loadmap();
		mz2.loadmap();
		int dx=0;
		int dy=0;
		for (int y = 0; y < n; y++) {
			for (int x = 0; x < n; x++) {
				if (mz1.map[y][x] == 1) {
					dx=x;
					dy=y;
					break;
				}
			}
		}
		mz1.scroll(dx, dy);
//		mz1.printmap();
		for (int j=0; j<4; j++) {
			dx=0;
			dy=0;
			for (int y = 0; y < n; y++) {
				for (int x = 0; x < n; x++) {
					if (mz2.map[y][x] == 1) {
						dx=x;
						dy=y;
						break;
					}
				}
			}
			mz2.scroll(dx, dy);
//			mz2.printmap();
			boolean bmiss=false;
			for (int y = 0; !bmiss && y < n; y++) {
				for (int x = 0; x < n; x++) {
					if (mz1.maps[y][x]!=mz2.maps[y][x]) {
						bmiss=true;
						break;
					}
				}
			}
			if (!bmiss) {
				System.out.println("Yes");
				System.exit(0);
			}
			mz2.rotateRight();
		}
//		mz2.rotateRight();
//		mz2.rotateRight();
//		mz2.printmap();
		System.out.println("No");
	}
	static class Maze {
		int h;
		int w;
		int[][] map; //map of Maze
		int[][] maps; //map of Maze
		Maze(int h, int w) {
			this.h = h;
			this.w = w;
			this.map = new int[h][w];
		}
		void initmap() {
		}
		void loadmap() {
			for (int y = 0; y < h; y++) {
				String s = nextLine();
				for (int x = 0; x < w; x++) {
					char ch = s.charAt(x);
					if (ch == '.') {
						map[y][x] = 0;
					} else if (ch == '#') {
						map[y][x] = 1;
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
		void rotateRight() {
			int[][] map2 = new int[h][w];
			for (int y = 0; y < h; y++) {
				for (int x = 0; x < w; x++) {
					map2[x][h-1-y]=map[y][x];
				}
			}
			map=map2;
		}
		void scroll(int dx, int dy) {
			maps = new int[h][w];
			for (int y = 0; y < h; y++) {
				for (int x = 0; x < w; x++) {
					int yy=(y+dy)%h;
					int xx=(x+dx)%w;
					maps[y][x]=map[yy][xx];
				}
			}
		}
		void printmap() {
			final String tbl = " .#123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
			if (DEBUG) {
				for (int y = 0; y < h; y++) {
					for (int x = 0; x < w; x++) {
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
8 63
...............................................................
..S...#............................#####..#####..#####..####G..
..#...#................................#..#...#......#..#......
..#####..####...####..####..#..#...#####..#...#..#####..#####..
..#...#..#..#...#..#..#..#..#..#...#......#...#..#..........#..
..#...#..#####..####..####..####...#####..#####..#####..#####..
................#.....#........#...............................
................#.....#........#...............................
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
