import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		Point[] aa=new Point[n];
		Point[] ab=new Point[n];
		for (int i=0; i<n; i++) {
			aa[i]=new Point(sc.nextInt(), i);
			ab[i]=new Point(sc.nextInt(), i);
		}
		Arrays.sort(aa);
		Arrays.sort(ab);
//		System.out.println(Arrays.toString(aa));
//		System.out.println(Arrays.toString(ab));
		int ans=0;
		if (aa[0].ed!=ab[0].ed) {
			ans=Math.max(aa[0].st, ab[0].st);
		} else {
			int s1=aa[0].st+ab[0].st;
			int s2=Math.max(aa[0].st, ab[1].st);
			int s3=Math.max(aa[1].st, ab[0].st);
			ans=Math.min(Math.min(s1, s2), s3);
			System.out.println(ans);
		}
//		int s1=aa[0]+ab[0];
//		int s2=Math.max(aa[0], ab[1]);
//		int s3=Math.max(aa[1], ab[0]);
//		int ans=Math.min(Math.min(s1, s2), s3);
//		System.out.println(ans);
	}
	static class Point implements Comparable<Point> {
		final int st;
		final int ed;
		Point(int st, int ed) {
			this.st = st;
			this.ed = ed;
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
			return "(" + st + "," + ed + ")";
		}
	}
}
/*
3
8 5
4 4
7 9

3
11 7
3 2
6 7
*/
