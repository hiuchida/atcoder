import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = 4;
		Point[] ary=new Point[n];
		for (int i=0; i<n; i++) {
			ary[i]=new Point(sc.nextInt(), sc.nextInt());
		}
		System.out.println(Arrays.toString(ary));
		System.out.println("Yes");
		System.out.println("No");
		System.exit(0);
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
0 0
1 0
1 1
0 1

0 0
1 1
-1 0
1 -1
*/
