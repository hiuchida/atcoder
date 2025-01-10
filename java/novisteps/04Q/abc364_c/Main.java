import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static long ans = Integer.MAX_VALUE;
	static int n;
	static TreeMap<Integer,Integer> map = new TreeMap<>();
	static List<Point> list = new ArrayList<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		long x = sc.nextLong();
		long y = sc.nextLong();
		int[] aa = new int[n];
		for (int i=0; i<n; i++) {
			aa[i] = sc.nextInt();
		}
		int[] ab = new int[n];
		for (int i=0; i<n; i++) {
			ab[i] = sc.nextInt();
		}
		long sa = 0;
		long sb = 0;
		for (int i=0; i<n; i++) {
			list.add(new Point(aa[i], ab[i]));
			sa += aa[i];
			sb += ab[i];
		}
		if (sa <= x && sb <= y) {
			System.out.println(n);
			System.exit(0);
		}
		Arrays.sort(aa);
		Arrays.sort(ab);
		Collections.sort(list, new Comparator<Point>() {
			@Override
			public int compare(Point p1, Point p2) {
				return -Integer.compare(p1.st, p2.st);
			}
			
		});
		sa = 0;
		sb = 0;
		for (int i=0; i<n; i++) {
			sa += list.get(i).st;
			sb += list.get(i).ed;
			if (sa > x || sb > y) ans = Math.min(ans, i+1);
		}
		Collections.sort(list, new Comparator<Point>() {
			@Override
			public int compare(Point p1, Point p2) {
				return -Integer.compare(p1.ed, p2.ed);
			}
			
		});
		sa = 0;
		sb = 0;
		for (int i=0; i<n; i++) {
			sa += list.get(i).st;
			sb += list.get(i).ed;
			if (sa > x || sb > y) ans = Math.min(ans, i+1);
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
			int cmp = Integer.compare(this.st+this.ed, that.st+that.ed);
			return cmp;
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
4 7 18
2 3 5 1
8 8 1 4

5 200000000000000 200000000000000
1 1 1 1 1
2 2 2 2 2

8 30 30
1 2 3 4 5 6 7 8
8 7 6 5 4 3 2 1
*/
