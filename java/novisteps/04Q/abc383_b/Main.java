import java.util.*;
public class Main {
	static int h;
	static int w;
	static int d;
	static Maze mz;
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		h = sc.nextInt();
		w = sc.nextInt();
		d = sc.nextInt();
		sc.nextLine();
		mz = new Maze(h, w);
		mz.initmap();
		mz.loadmap(sc);
//		mz.printmap();
		int ans = 0;
		for (int y = 1; y <= h; y++) {
			for (int x = 1; x <= w; x++) {
				if (mz.map[y][x]!=0) continue;
				Set<Point> set = new TreeSet<>();
				fill(set, y, x);
//				System.out.println(y + " " + x + " " + set);
				for (int u = 1; u <= h; u++) {
					for (int v = 1; v <= w; v++) {
						if (y == u && x == v) continue;
						if (mz.map[u][v]!=0) continue;
						Set<Point> set2 = new TreeSet<>(set);
						fill(set2, u, v);
						ans = Math.max(ans, set2.size());
					}
				}
			}
		}
		System.out.println(ans);
	}
	static void fill(Set<Point> set, int y, int x) {
		for (int dy = -d; dy <= d; dy++) {
			int y1 = y + dy;
			if (y1 < 1 || y1 > h) continue;
			for (int dx = -d; dx <= d; dx++) {
				if (Math.abs(dy) + Math.abs(dx) > d) continue;
				int x1 = x + dx;
				if (x1 < 1 || x1 > w) continue;
				if (mz.map[y1][x1]==0) {
					set.add(new Point(y1, x1));
				}
			}
		}
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
		void loadmap(Scanner sc) {
			for (int y = 1; y <= h; y++) {
				String s = sc.nextLine();
				for (int x = 1; x <= w; x++) {
					char ch = s.charAt(x - 1);
					if (ch == '.') {
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
			final String tbl = "#.123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
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
	static class Point implements Comparable<Point> {
		int y;
		int x;
		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
		@Override
		public int compareTo(Main.Point that) {
			int cmp = Integer.compare(this.y, that.y);
			if (cmp != 0) return cmp;
			return Integer.compare(this.x, that.x);
		}
		@Override
		public String toString() {
			return "(" + y + "," + x + ")";
		}
	}
}
/*
2 5 1
.###.
.#.##

5 5 2
.#.#.
.....
.#.#.
#.#.#
.....

4 4 2
....
.##.
.##.
....
*/
