import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static final boolean RELEASE = true;
	void solve() {
		Scanner sc=new Scanner(System.in);
		init(sc);
		for (int i=0; i<n; i+=k) {
			for (int j=1; j<k; j++) {
				System.out.println(0+" "+i+" "+(i+j));
			}
		}
	}
	int n;
	int t;
	int m;
	int k;
	int l;
	Dot[] ad;
	void init(Scanner sc) {
		n=sc.nextInt();
		t=sc.nextInt();
		m=sc.nextInt();
		k=sc.nextInt();
		l=sc.nextInt();
		ad=new Dot[n];
		for (int i=0; i<n; i++) {
			int x=sc.nextInt();
			int y=sc.nextInt();
			int vx=sc.nextInt();
			int vy=sc.nextInt();
			ad[i]=new Dot(x, y, vx, vy);
		}
//		System.out.println(Arrays.toString(ad));
	}
	static Random rand=new Random(42);
	static long start;
	public static void main(String[] args) {
		start = System.currentTimeMillis();
		new Main().solve();
	}
	static class Dot {
		int x;
		int y;
		int vx;
		int vy;
		Dot(int x, int y, int vx, int vy) {
			this.x=x;
			this.y=y;
			this.vx=vx;
			this.vy=vy;
		}
		@Override
		public String toString() {
			return "(" + x + "," + y + "," + vx + "," + vy + ")";
		}
	}
	static class Point implements Comparable<Point> {
		final int x;
		final int y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		static Comparator<Point> newComparator1() {
			return new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					int cmp = Integer.compare(o1.x, o2.x);
					return cmp;
				}
			};
		}
		static Comparator<Point> newComparator2() {
			return new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					int cmp = Integer.compare(o1.y, o2.y);
					return cmp;
				}
			};
		}
		@Override
		public int compareTo(Point that) {
			int cmp = Integer.compare(this.x, that.x);
			if (cmp != 0) return cmp;
			return Integer.compare(this.y, that.y);
		}
		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}
		@Override
		public boolean equals(Object obj) {
			Point that = (Point) obj;
			return this.x == that.x && this.y == that.y;
		}
		@Override
		public String toString() {
			return "(" + x + "," + y + ")";
		}
	}
}
/*



*/
