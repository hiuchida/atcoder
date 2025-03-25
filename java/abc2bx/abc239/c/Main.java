import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Point p1=new Point(sc.nextInt(), sc.nextInt());
		Point p2=new Point(sc.nextInt(), sc.nextInt());
		TreeSet<Point> set=new TreeSet<>();
		int[] dx={1,2,2,1,-1,-2,-2,-1};
		int[] dy={2,1,-1,-2,-2,-1,1,2};
		for (int d=0; d<dx.length; d++) {
			set.add(new Point(p1.x+dx[d], p1.y+dy[d]));
		}
		for (int d=0; d<dx.length; d++) {
			if (set.contains(new Point(p2.x+dx[d], p2.y+dy[d]))) {
				System.out.println("Yes");
				System.exit(0);
			}
		}
		System.out.println("No");
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
0 0 3 3

0 1 2 3

1000000000 1000000000 999999999 999999999
*/
