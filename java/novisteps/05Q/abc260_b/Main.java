import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int x = sc.nextInt();
		int y = sc.nextInt();
		int z = sc.nextInt();
		Point[] ary=new Point[n];
		for (int i=0; i<n; i++) {
			int a = sc.nextInt();
			ary[i]=new Point(a, 0, i+1);
		}
		for (int i=0; i<n; i++) {
			int b = sc.nextInt();
			ary[i].ed=b;
		}
		TreeSet<Integer> set=new TreeSet<>();
		Arrays.sort(ary, Point.newComparator1());
//		System.out.println(Arrays.toString(ary));
		int cnt=0;
		for (int i=0; i<n && cnt<x; i++) {
			int v=ary[i].idx;
			if (set.contains(v)) continue;
			set.add(v);
			cnt++;
		}
		Arrays.sort(ary, Point.newComparator2());
//		System.out.println(Arrays.toString(ary));
		cnt=0;
		for (int i=0; i<n && cnt<y; i++) {
			int v=ary[i].idx;
			if (set.contains(v)) continue;
			set.add(v);
			cnt++;
		}
		Arrays.sort(ary, Point.newComparator3());
//		System.out.println(Arrays.toString(ary));
		cnt=0;
		for (int i=0; i<n && cnt<z; i++) {
			int v=ary[i].idx;
			if (set.contains(v)) continue;
			set.add(v);
			cnt++;
		}
		for (int v : set) {
			System.out.println(v);
		}
	}
	static class Point implements Comparable<Point> {
		final int st;
		int ed;
		final int idx;
		Point(int st, int ed, int idx) {
			this.st = st;
			this.ed = ed;
			this.idx = idx;
		}
		static Comparator<Point> newComparator1() {
			return new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					int cmp = Integer.compare(o1.st, o2.st);
					if (cmp != 0) return -cmp;
					cmp = Integer.compare(o1.idx, o2.idx);
					return cmp;
				}
			};
		}
		static Comparator<Point> newComparator2() {
			return new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					int cmp = Integer.compare(o1.ed, o2.ed);
					if (cmp != 0) return -cmp;
					cmp = Integer.compare(o1.idx, o2.idx);
					return cmp;
				}
			};
		}
		static Comparator<Point> newComparator3() {
			return new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					int cmp = Integer.compare(o1.st+o1.ed, o2.st+o2.ed);
					if (cmp != 0) return -cmp;
					cmp = Integer.compare(o1.idx, o2.idx);
					return cmp;
				}
			};
		}
		@Override
		public int compareTo(Point that) {
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
			return "(" + st + "," + ed + "," + idx + ")";
		}
	}
}
/*
6 1 0 2
80 60 80 60 70 70
40 20 50 90 90 80

5 2 1 2
0 100 0 100 0
0 0 100 100 0

15 4 3 2
30 65 20 95 100 45 70 85 20 35 95 50 40 15 85
0 25 45 35 65 70 80 90 40 55 20 20 45 75 100
*/
