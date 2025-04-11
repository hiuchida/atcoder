import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] ary=new int[k];
		for (int i=0; i<k; i++) {
			ary[i]=sc.nextInt();
		}
		Point[] ap=new Point[n];
		for (int i=0; i<n; i++) {
			ap[i]=new Point(sc.nextInt(), sc.nextInt());
		}
		double ans=0;
		for (int i=0; i<n; i++) {
			double r=Integer.MAX_VALUE;
			Point p0=ap[i];
			for (int j=0; j<k; j++) {
				Point p1=ap[ary[j]-1];
				double x=calc(p0, p1);
				r=Math.min(r, x);
//				System.out.println(p0+" "+p1+" "+x+" "+r);
			}
//			System.out.println(p0+" "+r);
			ans=Math.max(ans, r);
		}
		System.out.println(ans);
	}
	static double calc(Point p1, Point p2) { //abc234_b,abc255_b,abc374_d: p1からp2までのユークリッド距離
		long dx=p1.x-p2.x;
		long dy=p1.y-p2.y;
		return Math.sqrt(dx*dx+dy*dy);
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
4 2
2 3
0 0
0 1
1 2
2 0

2 1
2
-100000 -100000
100000 100000

8 3
2 6 8
-17683 17993
93038 47074
58079 -57520
-41515 -89802
-72739 68805
24324 -73073
71049 72103
47863 19268
*/
