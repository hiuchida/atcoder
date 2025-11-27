import java.util.*;
public class Main {
	void solve() {
		Scanner sc=new Scanner(System.in);
		init(sc);
		int cnt=0;
		long end;
		while (true) {
			end=System.currentTimeMillis();
			if (end-start>=time_limit) break;
			if (cnt>=k) break;
			int idx1=rand.nextInt(d);
			Point p1=ap[idx1];
			int d1=0;
			if (idx1>0) {
				d1=calc(ap[idx1-1], p1);
			}
			int d2=0;
			if (idx1<d-1) {
				d2=calc(p1, ap[idx1+1]);
			}
			if (d1+d2<=5) continue;
			int score1=score();
			while (true) {
				end=System.currentTimeMillis();
				if (end-start>=time_limit) break;
				int y2=rand.nextInt(h);
				int x2=rand.nextInt(w);
				if (map[y2][x2]!=0) continue;
				Point p2=new Point(y2, x2);
				ap[idx1]=p2;
				int score2=score();
				if (score1<score2) {
					ap[idx1]=p1;
					continue;
				}
				map[p2.y][p2.x]=map[p1.y][p1.x];
				map[p1.y][p1.x]=0;
				System.out.println(p1.y+" "+p1.x+" "+p2.y+" "+p2.x);
//				System.out.println(p1.y+" "+p1.x+" "+p2.y+" "+p2.x+" "+score1+" "+score2);
				cnt++;
				break;
			}
		}
		end=System.currentTimeMillis();
//		System.err.println("elaps:"+(end-start));
//		System.err.println("score:"+score());
//		print();
	}
	int score() {
		int sum=0;
		for (int i=0; i<d-1; i++) {
			sum+=calc(ap[i], ap[i+1]);
		}
		return sum;
	}
	int h;
	int w;
	int d;
	int k;
	Point[] ap;
	int[][] map;
	Pair[] apr;
	void init(Scanner sc) {
		h=sc.nextInt();
		w=sc.nextInt();
		d=sc.nextInt();
		k=sc.nextInt();
		ap=new Point[d];
		map=new int[h][w];
		apr=new Pair[d-1];
		for (int i=0; i<d; i++) {
			int y=sc.nextInt();
			int x=sc.nextInt();
			ap[i]=new Point(y, x);
			map[y][x]=i+1;
		}
		for (int i=0; i<d-1; i++) {
			int v=calc(ap[i], ap[i+1]);
			apr[i]=new Pair(i, v);
		}
		Arrays.sort(apr);
//		System.out.println(Arrays.toString(apr));
	}
	void print() {
		System.out.println(Arrays.toString(ap));
		for (int y=0; y<h; y++) {
			System.out.println(Arrays.toString(map[y]));
		}
	}
	static final int[] DY = { -1, 1, 0, 0 }; //U,D,L,R
	static final int[] DX = {  0, 0,-1, 1 }; //U,D,L,R
	static final String DS = "UDLR";
	static Random rand=new Random(42);
	static long start;
	static final int time_limit=3800;
	public static void main(String[] args) {
		start=System.currentTimeMillis();
		new Main().solve();
	}
	//abc035_b,abc057_b,abc086_c,abc295_b: x1,y1からx2,y2までのマンハッタン距離
	static int calc(Point p1, Point p2) {
		return calc(p1.y, p1.x, p2.y, p2.x);
	}
	static int calc(int x1, int y1, int x2, int y2) {
		int dx=x1-x2;
		int dy=y1-y2;
		return Math.abs(dx)+Math.abs(dy);
	}
	static class Pair implements Comparable<Pair> {
		final int st;
		final int ed;
		Pair(int st, int ed) {
			this.st = st;
			this.ed = ed;
		}
		@Override
		public int compareTo(Pair that) {
			return -Long.compare(this.ed, that.ed);
		}
		@Override
		public int hashCode() {
			return Objects.hash(st, ed);
		}
		@Override
		public boolean equals(Object obj) {
			Pair that = (Pair) obj;
			return this.st == that.st && this.ed == that.ed;
		}
		@Override
		public String toString() {
			return "(" + st + "," + ed + ")";
		}
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
}
/*



*/
