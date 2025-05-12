import java.util.*;
public class Main {
	static final boolean DEBUG = true;
	static final int[] DY = { -1, 1, 0, 0 }; //U,D,L,R
	static final int[] DX = {  0, 0,-1, 1 }; //U,D,L,R
	static final String DS = "^v<>";
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int h = sc.nextInt();
		int w = sc.nextInt();
		sc.nextLine();
		Maze mz;
		mz = new Maze(h, w);
		mz.initmap();
		mz.loadmap(sc);
//		System.out.println(mz.lst);
		Deque<Point> que=new ArrayDeque<>();
		for (Point p : mz.lst) {
			que.add(p);
		}
		while (que.size()>0) {
			Point p=que.poll();
			int s=mz.map[p.y][p.x];
			for (int d=0; d<DY.length; d++) {
				int y=p.y+DY[d];
				int x=p.x+DX[d];
				if (mz.map[y][x]<0) continue;
				if (mz.map[y][x]!=0) continue;
				mz.map[y][x]=s+1;
				que.add(new Point(y, x));
			}
		}
//		mz.printmap();
//		System.out.println(Arrays.toString(ary));
//		System.out.println();
		for (int y = 1; y < h + 1; y++) {
			for (int x = 1; x < w + 1; x++) {
				int i=mz.map[y][x];
				if (i<0) System.out.print('#');
				else if (i==1) System.out.print('E');
				else {
					for (int d=0; d<DY.length; d++) {
						int y2=y+DY[d];
						int x2=x+DX[d];
						int i2=mz.map[y2][x2];
						if (i-1==i2) {
							System.out.print(DS.charAt(d));
							break;
						}
					}
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	static class Point implements Comparable<Point> {
		final int y;
		final int x;
		Point(int y, int x) {
			this.y = y;
			this.x = x;
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
	static class Maze { //Maze250408
		int h;
		int w;
		int[][] map; //map of Maze
		List<Point> lst=new ArrayList<>();
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
					} else if (ch == 'E') {
						map[y][x] = 1;
						lst.add(new Point(y, x));
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
3 4
...E
.#..
....

3 2
##
##
##

7 20
....................
..#..#..####..#E##..
..#..#..#..#..#.....
..E###..#..#..####..
.....#..#..E.....#..
.....#..####..####..
....................
*/
