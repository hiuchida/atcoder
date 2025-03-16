import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Point[] ary = new Point[n];
		for (int i=0; i<ary.length; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			ary[i]=new Point(x, y);
		}
		long ans = 0;
		for (int i=0; i<n; i++) {
			for (int j=i+1; j<n; j++) {
				for (int k=j+1; k<n; k++) {
					if (is(ary[i], ary[j], ary[k])) ans++;
				}
			}
		}
		System.out.println(ans);
	}
	static boolean is(Point p1, Point p2, Point p3) {
		if (p1.x==p2.x && p2.x==p3.x) return false;
		if (p1.y==p2.y && p2.y==p3.y) return false;
		long dx12=p1.x-p2.x;
		long dx23=p2.x-p3.x;
		long dy12=p1.y-p2.y;
		long dy23=p2.y-p3.y;
		if (dx12*dy23==dx23*dy12) return false;
		return true;
	}
	static class Point implements Comparable<Point> {
		final int y;
		final int x;
		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
		@Override
		public int compareTo(Main.Point that) {
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
0 1
1 3
1 1
-1 -1

20
224 433
987654321 987654321
2 0
6 4
314159265 358979323
0 0
-123456789 123456789
-1000000000 1000000000
124 233
9 -6
-4 0
9 5
-7 3
333333333 -333333333
-9 -1
7 -10
-1 5
324 633
1000000000 -1000000000
20 0
*/
