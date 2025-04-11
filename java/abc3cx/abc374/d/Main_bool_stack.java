import java.util.*;

public class Main {
	static double ans = 0;
	static int n;
	static int s;
	static int t;
	static double calcmove(Point fm, Point to) {
		return calc(fm, to) / s;
	}
	static double calc(Point p1, Point p2) { //abc234_b,abc255_b,abc374_d: p1からp2までのユークリッド距離
		long dx=p1.x-p2.x;
		long dy=p1.y-p2.y;
		return Math.sqrt(dx*dx+dy*dy);
	}
	static class Point implements Comparable<Point> {
		int y;
		int x;
		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
		@Override
		public int compareTo(Point that) {
			int cmp = Integer.compare(this.y, that.y);
			if (cmp != 0) return cmp;
			return Integer.compare(this.x, that.x);
		}
		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (obj == null) return false;
			if (getClass() != obj.getClass()) return false;
			Point other = (Point) obj;
			return x == other.x && y == other.y;
		}
		@Override
		public String toString() {
			return "(" + y + ", " + x + ")";
//			return "Point [y=" + y + ", x=" + x + "]";
		}
	}
	static class Line {
		Point fm;
		Point to;
		double dt;
		Line(Point fm, Point to) {
			this.fm = fm;
			this.to = to;
			this.dt = calc(fm, to) / t;
		}
		@Override
		public String toString() {
			return "Line [fm=" + fm + ", to=" + to + "]";
		}
	}
	static Line[] lines;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		s = sc.nextInt();
		t = sc.nextInt();
		lines = new Line[n];
		for (int i=0; i<n; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			int d = sc.nextInt();
			Point fm = new Point(a, b);
			Point to = new Point(c, d);
			lines[i] = new Line(fm, to);
		}
//		for (int i=0; i<n; i++) {
//			System.out.println(lines[i]);
//		}
		ans = Integer.MAX_VALUE;
		double ans0 = 0;
		Point pt = new Point(0, 0);
		boolean[] flag = new boolean[n];
		Stack<String> stack = new Stack<>();
		dfs(pt, ans0, flag, stack);
		/*
		ans += lines[1].dt / t;
		pt = lines[1].fm;
		ans += calcmove(pt, lines[0].fm);
		ans += lines[0].dt / t;
		pt = lines[0].to;
		ans += calcmove(pt, lines[2].to);
		ans += lines[2].dt / t;
		*/
		System.out.println(ans);
	}
	static void dfs(Point pt0, double ans0, boolean[] flag, Stack<String> stack) {
//		if (ans0 > ans) return;
		if (stack.size() == n) {
			ans = Math.min(ans, ans0);
			return;
		}
		for (int i=0; i<n; i++) {
			for (int j=0; j<2; j++) {
				if (flag[i]) continue;
				Point pt = pt0;
				Point pt2 = j==0 ? lines[i].fm : lines[i].to;
				Point pt3 = j==1 ? lines[i].fm : lines[i].to;
				double ans1 = ans0;
				ans1 += calcmove(pt, pt2);
				ans1 += lines[i].dt;
				flag[i] = true;
				stack.push(i + "_" + (j==0 ? "L":"R"));
//				System.out.println(pt + " " + pt2 + " " + pt3 + " " + ans0 + " " + + ans1 + " " + stack);
				pt = pt3;
				dfs(pt, ans1, flag, stack);
				flag[i] = false;
				stack.pop();
			}
		}
	}
}
/*
6.44317475868633722080
6.443174758686338

*/
