import java.util.*;
public class Main {
	void solve() {
		Scanner sc=new Scanner(System.in);
		init(sc);
		int n2=(int)Math.sqrt(n);
		for (int k=0; k<t; k++) {
			Answer ans=new Answer();
			for (int i=0; i<n; i++) {
				int k2=(i+1)%n2;
				int r=0;
				char d=DS.charAt(0);
				int b;
				if (k2==1) b=-1;
				else b=i-1;
				ans.add(new Prdb(i, r, d, b));
			}
			ans.print();
			int w=sc.nextInt();
			int h=sc.nextInt();
//			System.err.println("w="+w+", h="+h);
		}
	}
	class Prdb {
		int p;
		int r;
		char d;
		int b;
		Prdb(int p, int r, char d, int b) {
			this.p=p;
			this.r=r;
			this.d=d;
			this.b=b;
		}
		@Override
		public String toString() {
			return p+" "+r+" "+d+" "+b;
		}
	}
	class Answer {
		List<Prdb> lp=new ArrayList<>();
		void add(Prdb prdb) {
			lp.add(prdb);
		}
		void print() {
			System.out.println(lp.size());
			for (Prdb prdb : lp) {
				System.out.println(prdb.toString());
			}
			System.out.flush();
		}
	}
	final String DS="UL";
	int n;
	int t;
	int s;
	Point[] ap;
	void init(Scanner sc) {
		n=sc.nextInt();
		t=sc.nextInt();
		s=sc.nextInt();
		ap=new Point[n];
		for (int i=0; i<n; i++) {
			int w=sc.nextInt();
			int h=sc.nextInt();
			ap[i]=new Point(w, h);
		}
//		System.out.println(Arrays.toString(ap));
	}
	static Random rand=new Random(42);
	static long start;
	public static void main(String[] args) {
		start = System.currentTimeMillis();
		new Main().solve();
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
