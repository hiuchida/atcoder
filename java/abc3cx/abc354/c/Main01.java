import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		TreeSet<Point> set1=new TreeSet<>();
		TreeSet<Point2> set2=new TreeSet<>();
		for (int i=0; i<n; i++) {
			int a = sc.nextInt();
			int c = sc.nextInt();
			set1.add(new Point(a, c, i+1));
			set2.add(new Point2(a, c, i+1));
		}
//		System.out.println(set1);
//		System.out.println(set2);
		TreeSet<Point3> set3=new TreeSet<>();
		while (set1.size()>0) {
			Point p1=set1.last();
			set1.remove(p1);
			set2.remove(new Point2(p1.st, p1.ed, p1.idx));
			set3.add(new Point3(p1.st, p1.ed, p1.idx));
			while (set2.size()>0) {
				Point2 p2=set2.last();
//				System.out.println(p1+" "+p2);
				if (p2.ed<p1.ed) break;
				set2.remove(p2);
				set1.remove(new Point(p2.st, p2.ed, p2.idx));
			}
		}
//		System.out.println(set1);
//		System.out.println(set2);
//		System.out.println(set3);
		for (Point3 p3 : set3) {
			System.out.print(p3.idx+" ");
		}
		System.out.println();
	}
	static class Point implements Comparable<Point> {
		final int st;
		final int ed;
		final int idx;
		Point(int st, int ed, int idx) {
			this.st = st;
			this.ed = ed;
			this.idx = idx;
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
			return "(" + st + "," + ed + "," + idx + ")";
		}
	}
	static class Point2 implements Comparable<Point2> {
		final int st;
		final int ed;
		final int idx;
		Point2(int st, int ed, int idx) {
			this.st = st;
			this.ed = ed;
			this.idx = idx;
		}
		@Override
		public int compareTo(Main.Point2 that) {
			int cmp = Integer.compare(this.ed, that.ed);
			if (cmp != 0) return cmp;
			return Integer.compare(this.st, that.st);
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
	static class Point3 implements Comparable<Point3> {
		final int st;
		final int ed;
		final int idx;
		Point3(int st, int ed, int idx) {
			this.st = st;
			this.ed = ed;
			this.idx = idx;
		}
		@Override
		public int compareTo(Main.Point3 that) {
			int cmp = Integer.compare(this.idx, that.idx);
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
			return "(" + st + "," + ed + "," + idx + ")";
		}
	}
}
/*
3
2 4
1 1
3 2

5
1 1
10 2
100 3
1000 4
10000 5

6
32 101
65 78
2 29
46 55
103 130
52 40
*/
