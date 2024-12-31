import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int h = sc.nextInt();
		int w = sc.nextInt();
		int q = sc.nextInt();
		TreeSet<Point> set = new TreeSet<>();
		for (int i=0; i<q; i++) {
			int r = sc.nextInt();
			int c = sc.nextInt();
			Point p = new Point(r, c);
//			System.out.println("a " + p);
			if (!set.contains(p)) {
//				System.out.println("A " + p);
				set.add(p);
			} else {
				for (int y=p.y; y>=1; y--) {
					Point p2 = new Point(y, p.x);
					if (!set.contains(p2)) {
//						System.out.println("U " + p2);
						set.add(p2);
						break;
					}
				}
				for (int y=p.y; y<=h; y++) {
					Point p2 = new Point(y, p.x);
//					System.out.println("d " + p2 + " " + set);
					if (!set.contains(p2)) {
//						System.out.println("D " + p2);
						set.add(p2);
						break;
					}
				}
				for (int x=p.x; x>=1; x--) {
					Point p2 = new Point(p.y, x);
					if (!set.contains(p2)) {
//						System.out.println("L " + p2);
						set.add(p2);
						break;
					}
				}
				for (int x=p.x; x<=w; x++) {
					Point p2 = new Point(p.y, x);
					if (!set.contains(p2)) {
//						System.out.println("R " + p2);
						set.add(p2);
						break;
					}
				}
			}
		}
		long ans = h*w;
		ans -= set.size();
		System.out.println(ans);
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
			return "(" + y + ", " + x + ")";
		}
	}
}
