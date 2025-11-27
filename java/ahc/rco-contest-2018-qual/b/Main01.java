import java.util.*;
public class Main {
	void solve() {
		Scanner sc=new Scanner(System.in);
		init(sc);
		int idx=0;
		int cnt=0;
		for (int y=0; y<h; y++) {
			if (idx>=d) break;
			if (cnt>=k) break;
			for (int xx=0; xx<w; xx++) {
				if (idx>=d) break;
				if (cnt>=k) break;
				int x=(y%2==0) ? xx : w-1-xx;
				Point p0=ap[idx++];
				if (p0.y==y && p0.x==x) continue;
				if (map[p0.y][p0.x]!=idx) {
					System.err.println("debug "+p0+" "+idx);
				}
				int idx2=map[y][x];
				map[y][x]=idx;
				map[p0.y][p0.x]=idx2;
				if (idx2>0) {
					ap[idx-1]=ap[idx2-1];
					ap[idx2-1]=p0;
				} else {
					ap[idx-1]=new Point(y, x);
				}
				System.out.println(y+" "+x+" "+p0.y+" "+p0.x);
				cnt++;
			}
		}
//		print();
	}
	int h;
	int w;
	int d;
	int k;
	Point[] ap;
	int[][] map;
	void init(Scanner sc) {
		h=sc.nextInt();
		w=sc.nextInt();
		d=sc.nextInt();
		k=sc.nextInt();
		ap=new Point[d];
		map=new int[h][w];
		for (int i=0; i<d; i++) {
			int y=sc.nextInt();
			int x=sc.nextInt();
			ap[i]=new Point(y, x);
			map[y][x]=i+1;
		}
	}
	void print() {
		System.out.println(Arrays.toString(ap));
		for (int y=0; y<h; y++) {
			System.out.println(Arrays.toString(map[y]));
		}
	}
	static Random rand=new Random(42);
	static long start;
	static final int time_limit=3800;
	public static void main(String[] args) {
		start=System.currentTimeMillis();
		new Main().solve();
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
