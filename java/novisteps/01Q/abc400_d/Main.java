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
		int[][] dist=new int[h+1][w+1];
		Deque<Point> que=new ArrayDeque<>();
		que.addFirst(st);
		while (que.size()>0) {
			Point p=que.poll();
//			System.out.println(p);
			int y=p.y;
			int x=p.x;
			int s=p.s;
			if (dist[y][x]!=0) continue;
			dist[y][x]=s;
			for (int d=0; d<DY.length; d++) {
				int y1=y+DY[d];
				int x1=x+DX[d];
				int y2=y1+DY[d];
				int x2=x1+DX[d];
				if (mz.checkrange(y1, x1) && dist[y1][x1]==0) {
					if (mz.map[y1][x1]==0) {
						que.addFirst(new Point(y1, x1, s));
					} else {
						que.addLast(new Point(y1, x1, s+1));
					}
				}
				if (mz.checkrange(y2, x2) && dist[y2][x2]==0) {
					que.addLast(new Point(y2, x2, s+1));
				}
			}
		}
//		for (int y = 1; y <= h; y++) {
//			for (int x = 1; x <= w; x++) {
//				System.out.print(dist[y][x]+" ");
//			}
//			System.out.println();
//		}
		int ans=dist[ed.y][ed.x];
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
	static class Maze { //Maze250408
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
		boolean checkrange(int y, int x) {
			return 1<=y && y<=h && 1<=x && x<=w;
		}
		boolean check(int y, int x, int s) {
			if (checkrange(y, x)) {
				if (map[y][x]<0) return false;
				if (map[y][x]==0) return true;
				if (map[y][x]<=s) return false;
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
