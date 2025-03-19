import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static final int[] DY = { -1, 1, 0, 0 }; //UDLR
	static final int[] DX = {  0, 0,-1, 1 }; //UDLR
	static Point st;
	static Point ed;
	static Map<Point,Integer> map=new TreeMap<>(Point.newComparator1());
	static Deque<Point> que0=new ArrayDeque<>();
	static PriorityQueue<Point> que=new PriorityQueue<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int h = sc.nextInt();
		int w = sc.nextInt();
		sc.nextLine();
		Maze mz;
		mz = new Maze(h, w);
		mz.initmap();
		mz.loadmap(sc);
		mz.printmap();
//		System.out.println(st);
//		System.out.println(ed);
		que0.offer(st);
		while (que0.size()>0) {
			Point p=que0.poll();
			if (p.equals(ed)) {
				que0.offer(ed);
				break;
			}
			mz.map[p.y][p.x]=1;
			for (int d=0; d<DY.length; d++) {
				int m=mz.map[p.y+DY[d]][p.x+DX[d]];
				if (m==0) que0.offer(new Point(p.y+DY[d], p.x+DX[d], 0));
			}
		}
		if (que0.size()==0) {
			System.out.println("No");
		}
		mz.resetmap();
		int n = sc.nextInt();
		for (int i=0; i<n; i++) {
			int r = sc.nextInt();
			int c = sc.nextInt();
			int e = sc.nextInt();
			Point p=new Point(r, c, e);
			map.put(p, e);
		}
//		System.out.println(map);
		Point p=st;
		que.offer(p);
		while (que.size()>0) {
//			System.out.println(que);
			p=que.poll();
			int pe=p.e;
			Integer e=map.get(p);
			if (e!=null && pe<e) {
				pe=e;
			}
			mz.map[p.y][p.x]=pe+1;
			if (p.equals(ed)) {
				mz.printmap();
				System.out.println("Yes");
				System.exit(0);
			}
			if (pe>0) {
				pe--;
				for (int d=0; d<DY.length; d++) {
					int m=mz.map[p.y+DY[d]][p.x+DX[d]];
					if (m != -1 && m <= pe) {
						que.offer(new Point(p.y+DY[d], p.x+DX[d], pe));
					}
				}
			}
		}
		mz.printmap();
		System.out.println("No");
	}
	static class Point implements Comparable<Point> {
		final int y;
		final int x;
		final int e;
		Point(int y, int x, int e) {
			this.y = y;
			this.x = x;
			this.e = e;
		}
		static Comparator<Point> newComparator1() {
			return new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					int cmp = Integer.compare(o1.y, o2.y);
					if (cmp != 0) return cmp;
					cmp = Integer.compare(o1.x, o2.x);
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
			int cmp = Integer.compare(this.e, that.e);
			return -cmp;
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
			return "(" + y + "," + x + ":" + e + ")";
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
						map[y][x] = 0;
						st=new Point(y, x, 0);
					} else if (ch == 'T') {
						map[y][x] = 0;
						ed=new Point(y, x, 0);
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
4 4
S...
#..#
#...
..#T
4
1 1 3
1 3 5
3 2 1
2 3 1

2 2
S.
T.
1
1 2 4

4 5
..#..
.S##.
.##T.
.....
3
3 1 5
1 2 3
2 2 1
*/
