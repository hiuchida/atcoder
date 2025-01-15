import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		List<Point> list = new ArrayList<>();
		for (int i=0; i<n; i++) {
			int p = sc.nextInt();
			list.add(new Point(p, i+1));
		}
		Collections.sort(list);
		List<String> lst = new ArrayList<>();
		for (int i=0; i<n; i++) {
			lst.add(""+list.get(i).ed);
		}
		String s=String.join(" ", lst);
		System.out.println(s);
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
2 3 1

3
1 2 3

5
5 3 2 4 1
*/
