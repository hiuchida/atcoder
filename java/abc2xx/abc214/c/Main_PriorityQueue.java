import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] as = new int[n];
		for (int i=0; i<as.length; i++) {
			as[i] = sc.nextInt();
		}
		int[] at = new int[n];
		for (int i=0; i<at.length; i++) {
			at[i] = sc.nextInt();
		}
		int[] ans = new int[n];
		Arrays.fill(ans, Integer.MAX_VALUE);
		PriorityQueue<Point> que = new PriorityQueue<>();
		for (int i=0; i<n; i++) {
			que.offer(new Point(at[i], i));
		}
		while (que.size()>0) {
			Point p=que.poll();
			if (ans[p.ed]<p.st) continue;
			ans[p.ed]=p.st;
			que.offer(new Point(p.st+as[p.ed], (p.ed+1)%n));
		}
		for (int i=0; i<n; i++) {
			System.out.println(ans[i]);
		}
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
3
4 1 5
3 10 100

4
100 100 100 100
1 1 1 1

4
1 2 3 4
1 2 4 7

8
84 87 78 16 94 36 87 93
50 22 63 28 91 60 64 27
*/
/*
3
4 1 1
9 9 2
*/
