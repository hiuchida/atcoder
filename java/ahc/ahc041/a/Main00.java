import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		init(sc);
//		System.out.println(Arrays.toString(aa));
//		System.out.println(Arrays.toString(apr));
//		System.out.println(Arrays.toString(apt));
		StringBuilder sb=new StringBuilder();
		for (int i=0; i<n; i++) {
			if (i>0) sb.append(" ");
			sb.append(-1);
		}
		System.out.println(sb.toString());
	}
	static int n;
	static int m;
	static int h;
	static int[] aa;
	static Pair[] apr;
	static Point[] apt;
	static void init(Scanner sc) {
		n=sc.nextInt();
		m=sc.nextInt();
		h=sc.nextInt();
		aa=new int[n];
		for (int i=0; i<n; i++) {
			int a=sc.nextInt();
			aa[i]=a;
		}
		apr=new Pair[m];
		for (int i=0; i<m; i++) {
			int u=sc.nextInt();
			int v=sc.nextInt();
			apr[i]=new Pair(u, v);
		}
		apt=new Point[n];
		for (int i=0; i<n; i++) {
			int x=sc.nextInt();
			int y=sc.nextInt();
			apt[i]=new Point(x, y);
		}
	}
	static class Pair implements Comparable<Pair> {
		final int st;
		final int ed;
		Pair(int st, int ed) {
			this.st = st;
			this.ed = ed;
		}
		static Comparator<Pair> newComparator1() {
			return new Comparator<Pair>() {
				@Override
				public int compare(Pair o1, Pair o2) {
					int cmp = Long.compare(o1.st, o2.st);
					return cmp;
				}
			};
		}
		static Comparator<Pair> newComparator2() {
			return new Comparator<Pair>() {
				@Override
				public int compare(Pair o1, Pair o2) {
					int cmp = Long.compare(o1.ed, o2.ed);
					return cmp;
				}
			};
		}
		@Override
		public int compareTo(Pair that) {
			int cmp = Long.compare(this.st, that.st);
			if (cmp != 0) return cmp;
			return Long.compare(this.ed, that.ed);
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
