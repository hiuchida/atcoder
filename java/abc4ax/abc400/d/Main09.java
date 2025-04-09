import java.util.*;
public class Main {
	static final boolean DEBUG = true;
	static final int[] DY = { -1, 1, 0, 0 }; //U,D,L,R
	static final int[] DX = {  0, 0,-1, 1 }; //U,D,L,R
	static int h;
	static int w;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		h = sc.nextInt();
		w = sc.nextInt();
		sc.nextLine();
		Maze mz;
		mz = new Maze(h, w);
		mz.initmap();
		mz.loadmap(sc);
//		mz.printmap();
		int a=sc.nextInt();
		int b=sc.nextInt();
		int c=sc.nextInt();
		int D=sc.nextInt();
		Point st=new Point(a, b, 1);
		Point ed=new Point(c, D, 1);
		Deque<Point> que=new ArrayDeque<>();
		que.addFirst(st);
		mz.map[st.y][st.x]=st.s;
		while (que.size()>0) {
			Point p=que.poll();
//			System.out.println(p);
			int y=p.y;
			int x=p.x;
			int s=p.s;
			for (int d=0; d<DY.length; d++) {
				int y1=y+DY[d];
				int x1=x+DX[d];
				int y2=y1+DY[d];
				int x2=x1+DX[d];
				if (mz.check(y1, x1, s)) {
					que.addFirst(new Point(y1, x1, s));
					mz.map[y1][x1]=s;
					if (mz.check(y2, x2, s)) {
						que.addFirst(new Point(y2, x2, s));
						mz.map[y2][x2]=s;
					}
				}
			}
			for (int d=0; d<DY.length; d++) {
				int y1=y+DY[d];
				int x1=x+DX[d];
				int y2=y1+DY[d];
				int x2=x1+DX[d];
				if (mz.range(y1, x1)) {
					for (int dd=0; dd<DY.length; dd++) {
						if (mz.check(y1+DY[dd], x1+DX[dd], s+1)) {
							que.addLast(new Point(y1+DY[dd], x1+DX[dd], s+1));
							mz.map[y1+DY[dd]][x1+DX[dd]]=s+1;
						}
					}
				}
				if (mz.check(y2, x2, s+1)) {
					que.addLast(new Point(y2, x2, s+1));
					mz.map[y2][x2]=s+1;
				}
				if (mz.range(y2, x2)) {
					for (int dd=0; dd<DY.length; dd++) {
						if (mz.check(y2+DY[dd], x2+DX[dd], s+1)) {
							que.addLast(new Point(y2+DY[dd], x2+DX[dd], s+1));
							mz.map[y2+DY[dd]][x2+DX[dd]]=s+1;
						}
					}
				}
			}
		}
//		mz.printmap();
		int ans=mz.map[ed.y][ed.x];
		ans--;
		System.out.println(ans);
	}
	static class Point implements Comparable<Point> {
		final int y;
		final int x;
		int s;
		Point(int y, int x, int s) {
			this.y = y;
			this.x = x;
			this.s = s;
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
			int cmp = Integer.compare(this.s, that.s);
			if (cmp != 0) return cmp;
			cmp = Integer.compare(this.y, that.y);
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
			return "(" + y + "," + x + "," + s + ")";
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
		boolean check(int y, int x, int p) {
			if (!range(y, x)) return false;
			if (map[y][x]<0) return false;
			if (map[y][x]==0) return true;
			if (map[y][x]>p) return true;
			return false;
		}
		boolean range(int y, int x) {
			return (1<=y && y<=h && 1<=x && x<=w);
		}
	}
}
/*
10 10
..........
#########.
#.......#.
#..####.#.
##....#.#.
#####.#.#.
.##.#.#.#.
###.#.#.#.
###.#.#.#.
#.....#...
1 1 7 1

2 2
.#
#.
1 1 2 2

1 3
.#.
1 1 1 3

20 20
####################
##...##....###...###
#.....#.....#.....##
#..#..#..#..#..#..##
#..#..#....##..#####
#.....#.....#..#####
#.....#..#..#..#..##
#..#..#.....#.....##
#..#..#....###...###
####################
####################
##..#..##...###...##
##..#..#.....#.....#
##..#..#..#..#..#..#
##..#..#..#..#..#..#
##.....#..#..#..#..#
###....#..#..#..#..#
#####..#.....#.....#
#####..##...###...##
####################
3 3 18 18
*/
/*
10 10
..........
#########.
#.......#.
#..####.#.
##....#.#.
#######.#.
....#.#.#.
#####.#.#.
###.#.#.#.
#.....#...
1 1 7 1
*/
