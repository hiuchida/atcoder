import java.util.*;
public class Main {
	static final boolean DEBUG = true;
	static final int[] DY = { -1, 1, 0, 0 }; //U,D,L,R
	static final int[] DX = {  0, 0,-1, 1 }; //U,D,L,R
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int h = sc.nextInt();
		int w = sc.nextInt();
		int ch=sc.nextInt();
		int cw=sc.nextInt();
		int dh=sc.nextInt();
		int dw=sc.nextInt();
		Point st=new Point(ch, cw, 1);
		Point ed=new Point(dh, dw, 0);
		sc.nextLine();
		Maze mz;
		mz = new Maze(h, w);
		mz.initmap();
		mz.loadmap(sc);
//		int ans=-1;
		Deque<Point> que=new ArrayDeque<>();
		que.addFirst(st);
		mz.map[st.y][st.x]=st.s;
		while (que.size()>0) {
			Point p=que.poll();
//			System.out.println(p);
//			if (p.y==ed.y && p.x==ed.x) {
//				ans=p.s-1;
//				break;
//			}
			for (int d=0; d<DY.length; d++) {
				int y=p.y+DY[d];
				int x=p.x+DX[d];
				int s=p.s;
				if (mz.check(y, x, s)) {
					que.addFirst(new Point(y, x, s));
					mz.map[y][x]=s;
				}
			}
			for (int dy=-2; dy<=2; dy++) {
				for (int dx=-2; dx<=2; dx++) {
					int y2=p.y+dy;
					int x2=p.x+dx;
					int s2=p.s+1;
					if (mz.check(y2, x2, s2)) {
						que.addLast(new Point(y2, x2, s2));
						mz.map[y2][x2]=s2;
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
		boolean check(int y, int x, int s) {
			if (1<=y && y<=h && 1<=x && x<=w) {
				if (map[y][x]<0) return false;
				if (map[y][x]==0) return true;
				if (map[y][x]<=s) return false;
				return true;
			}
			return false;
		}
	}
}
/*
4 4
1 1
4 4
..#.
..#.
.#..
.#..

4 4
1 4
4 1
.##.
####
####
.##.

4 4
2 2
3 3
....
....
....
....

4 5
1 2
2 5
#.###
####.
#..##
#..##
*/
