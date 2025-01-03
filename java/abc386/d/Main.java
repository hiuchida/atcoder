import java.util.*;
public class Main {
	static void DEBUG(Object x) {
//		System.out.println(x);
	}
	static void DEBUG(long x) {
		DEBUG(""+x);
    }
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		List<Point> list = new ArrayList<>();
		for (int i=0; i<m; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			String c = sc.next();
			Point p = new Point(x, y, c);
			list.add(p);
		}
		Collections.sort(list);
		DEBUG(list);
		int miny = Integer.MAX_VALUE;
		int minx = Integer.MAX_VALUE;
		for (Point p : list) {
			DEBUG(p + " " + miny + " " + minx);
			if ("W".equals(p.s)) {
				miny=Math.min(miny, p.y);
				minx=Math.min(minx, p.x);
			} else {
				if (miny <= p.y && minx <= p.x) ng();
			}
		}
		ok();
	}
	static void ok() {
		System.out.println("Yes");
		System.exit(0);
	}
	static void ng() {
		System.out.println("No");
		System.exit(0);
	}
	static class Point implements Comparable<Point> {
		final int y;
		final int x;
		final String s;
		Point(int y, int x, String s) {
			this.y = y;
			this.x = x;
			this.s = s;
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
			return "(" + y + "," + x + ":" + s + ")";
		}
	}
}
