import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		Set<Point> set = new HashSet<>();
		int ans = 0;
		for (int i=0; i<m; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			Point p;
			if (u>v) {
				p=new Point(v,u);
			} else if (u<v) {
				p=new Point(u,v);
			} else {
				ans++;
				continue;
			}
			if (set.contains(p)) ans++;
			else set.add(p);
		}
		System.out.println(ans);
	}
	static class Point implements Comparable<Point> {
		final int st;
		final int ed;
		Point(int st, int ed) {
			this.st = st;
			this.ed = ed;
		}
		@Override
		public int compareTo(Main.Point that) {
			int cmp = Integer.compare(this.st, that.st);
			if (cmp != 0) return cmp;
			return Integer.compare(this.ed, that.ed);
		}
		@Override
		public int hashCode() {
			return Objects.hash(st, ed);
		}
		@Override
		public boolean equals(Object obj) {
			Point that = (Point) obj;
			return this.st == that.st && this.ed == that.ed;
		}
		@Override
		public String toString() {
			return "(" + st + "," + ed + ")";
		}
	}
}
/*
3 5
1 2
2 3
3 2
3 1
1 1

1 0

6 10
6 2
4 1
5 1
6 6
5 3
5 1
1 4
6 4
4 2
5 6
*/
