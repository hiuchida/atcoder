import java.util.*;
public class Main {
	static int n;
	static Set<Point> set = new TreeSet<>();
	static void add(int y, int x) {
		if (y < 1 || y > n || x < 1 || x > n) return;
		set.add(new Point(y, x));
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		int m = sc.nextInt();
		int[] dy = { 2, 1,-1,-2,-2,-1, 1, 2 };
		int[] dx = { 1, 2, 2, 1,-1,-2,-2,-1 };
		for (int i = 0; i < m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			add(a, b);
			for (int d=0; d<dy.length; d++) {
				add(a+dy[d], b+dx[d]);
			}
		}
		long ans = (long)n * n - set.size();
		System.out.println(ans);
	}

	static class Point implements Comparable<Point> {
		int y;
		int x;
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
		public String toString() {
			return "(" + y + ", " + x + ")";
//			return "Point [y=" + y + ", x=" + x + "]";
		}
	}
}
