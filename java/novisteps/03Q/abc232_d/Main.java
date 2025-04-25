import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static Maze mz;
	static int ans=1;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int h = sc.nextInt();
		int w = sc.nextInt();
		sc.nextLine();
		mz = new Maze(h, w);
		mz.initmap();
		mz.loadmap(sc);
		mz.printmap();
		bfs();
		System.out.println(ans);
	}
	static void bfs() {
		Deque<Point> que=new ArrayDeque<>();
		Point p=new Point(1, 1, 1);
		que.offer(p);
		while (que.size()>0) {
			p=que.poll();
//			System.out.println(p);
			mz.map[p.y][p.x]=p.d;
			if (p.d>ans) ans=p.d;
			if (mz.map[p.y+1][p.x]==0) {
				que.offer(new Point(p.y+1, p.x, p.d+1));
				mz.map[p.y+1][p.x]=1;
			}
			if (mz.map[p.y][p.x+1]==0) {
				que.offer(new Point(p.y, p.x+1, p.d+1));
				mz.map[p.y][p.x+1]=1;
			}
		}
	}
	static class Point implements Comparable<Point> {
		final int y;
		final int x;
		final int d;
		Point(int y, int x, int d) {
			this.y = y;
			this.x = x;
			this.d = d;
		}
		static Comparator<Point> newComparator1() {
			return new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					int cmp = Integer.compare(o1.y, o2.y);
					return cmp;
				}
			};
		}
		static Comparator<Point> newComparator2() {
			return new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					int cmp = Integer.compare(o1.x, o2.x);
					return cmp;
				}
			};
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
			return "(" + y + "," + x + ")";
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
					} else if (ch == 'S') {
						map[y][x] = 28;
					} else if (ch == 'G') {
						map[y][x] = 16;
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
}
/*
3 4
.#..
..#.
..##

1 1
.

5 5
.....
.....
.....
.....
.....
*/
