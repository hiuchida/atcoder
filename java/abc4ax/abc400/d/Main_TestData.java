import java.util.*;
public class Main {
	static final boolean DEBUG = true;
	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
		int h = 5;
		int w = 5;
		int a=1;
		int b=1;
		int c=5;
		int d=5;
//		sc.nextLine();
		Maze mz;
		mz = new Maze(h, w);
		for (int i=0; i < 1 << (h*w); i++) {
			mz.initmap(i);
			if (mz.map[a][b]<0 || mz.map[c][d]<0) continue;
//			mz.loadmap(sc);
			int ret99=main99(mz, a, b, c, d);
			int ret1=main1(mz, a, b, c, d);
			if (ret1!=ret99) {
				mz.printmap();
				System.out.println(i);
				System.out.println(ret1);
				System.out.println(ret99);
				break;
			}
		}
	}
	public static int main1(Maze mz, int a, int b, int c, int D) {
		int ret=0;
		final int[] DY = { -1, 1, 0, 0 }; //U,D,L,R
		final int[] DX = {  0, 0,-1, 1 }; //U,D,L,R
//		Scanner sc=new Scanner(System.in);
//		h = sc.nextInt();
//		w = sc.nextInt();
//		sc.nextLine();
//		Maze mz;
//		mz = new Maze(h, w);
//		mz.initmap();
//		mz.loadmap(sc);
//		mz.printmap();
//		int a=sc.nextInt();
//		int b=sc.nextInt();
//		int c=sc.nextInt();
//		int D=sc.nextInt();
		Point st=new Point(a, b, 1);
		Point ed=new Point(c, D, 1);
		PriorityQueue<Point> que=new PriorityQueue<>();
		que.add(st);
//		que.add(ed);
		while (que.size()>0) {
			Point p=que.poll();
			if (p.y==ed.y && p.x==ed.x) {
				ret=p.s-1;
				break;
			}
			if (mz.map[p.y][p.x]!=0 && mz.map[p.y][p.x]<p.s) continue;
			mz.map[p.y][p.x]=p.s;
			for (int d=0; d<DY.length; d++) {
				int y=p.y+DY[d];
				int x=p.x+DX[d];
				int y2=y+DY[d];
				int x2=x+DX[d];
				if (mz.check(y, x, p.s)) {
					que.add(new Point(y, x, p.s));
				}
				if (mz.checkrange(y, x) && mz.map[y][x]<0) {
					for (int dd=0; dd<DY.length; dd++) {
						if (mz.check(y+DY[dd], x+DX[dd], p.s+1)) {
							que.add(new Point(y+DY[dd], x+DX[dd], p.s+1));
						}
					}
				}
				if (mz.checkrange(y2, x2) && mz.map[y2][x2]<0) {
					for (int dd=0; dd<DY.length; dd++) {
						if (mz.check(y2+DY[dd], x2+DX[dd], p.s+1)) {
							que.add(new Point(y2+DY[dd], x2+DX[dd], p.s+1));
						}
					}
				}
			}
		}
//		mz.printmap();
		return ret;
	}
	public static int main99(Maze mz, int a, int b, int c, int D) {
		int ret=0;
		final int[] DY = { -1, 1, 0, 0 }; //U,D,L,R
		final int[] DX = {  0, 0,-1, 1 }; //U,D,L,R
//		Scanner sc=new Scanner(System.in);
//		h = sc.nextInt();
//		w = sc.nextInt();
//		sc.nextLine();
//		Maze mz;
//		mz = new Maze(h, w);
//		mz.initmap();
//		mz.loadmap(sc);
//		mz.printmap();
//		int a=sc.nextInt();
//		int b=sc.nextInt();
//		int c=sc.nextInt();
//		int D=sc.nextInt();
		Point st=new Point(a, b, 1);
		Point ed=new Point(c, D, 1);
		int[][] dist=new int[mz.h+1][mz.w+1];
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
		ret=ans;
		return ret;
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
		void initmap(int s) {
			for (int y = 0; y < h + 2; y++) {
				for (int x = 0; x < w + 2; x++) {
					if (y==0 || x==0 || y==h+1 || x==w+1) map[y][x] = -1;
					else {
						int idx=(y-1)*w+(x-1);
						int mask=1 << idx;
						if ((s&mask)>0) {
							map[y][x]=-1;
						} else {
							map[y][x]=0;
						}
					}
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



*/
