import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		TreeSet<Point> set1=new TreeSet<>(Point.newComparator1());
		TreeSet<Point> set2=new TreeSet<>(Point.newComparator2());
		for (int i=0; i<n; i++) {
			int a = sc.nextInt();
			int c = sc.nextInt();
			Point pt=new Point(a, c, i+1);
			set1.add(pt);
			set2.add(pt);
		}
//		System.out.println(set1);
//		System.out.println(set2);
		TreeSet<Integer> set3=new TreeSet<>();
		while (set1.size()>0) {
			Point p1=set1.last();
			set1.remove(p1);
			set2.remove(p1);
			set3.add(p1.idx);
			while (set2.size()>0) {
				Point p2=set2.last();
//				System.out.println(p1+" "+p2);
				if (p2.ed<p1.ed) break;
				set2.remove(p2);
				set1.remove(p2);
			}
		}
//		System.out.println(set1);
//		System.out.println(set2);
//		System.out.println(set3);
		System.out.println(set3.size());
		for (int i3 : set3) {
			System.out.print(i3+" ");
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
		static Comparator<Point> newComparator1() {
			return new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					int cmp = Integer.compare(o1.st, o2.st);
					return cmp;
				}
			};
		}
		static Comparator<Point> newComparator2() {
			return new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					int cmp = Integer.compare(o1.ed, o2.ed);
					return cmp;
				}
			};
		}
		static Comparator<Point> newComparator3() {
			return new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					int cmp = Integer.compare(o1.idx, o2.idx);
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
