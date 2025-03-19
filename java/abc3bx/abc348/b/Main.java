import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Point[] ary=new Point[n];
		for (int i=0; i<n; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			ary[i]=new Point(x, y);
		}
		for (int i=0; i<n; i++) {
			int idx=-1;
			long val=-1;
			for (int j=0; j<n; j++) {
				if (i!=j) {
					long x=calc(ary[i], ary[j]);
					if (x>val) {
						idx=j;
						val=x;
					}
				}
			}
			System.out.println(idx+1);
		}
	}
	static long calc(Point a, Point b) {
		return (a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y);
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
4
0 0
2 4
5 0
3 4

6
3 2
1 6
4 5
1 3
5 5
9 8
*/
