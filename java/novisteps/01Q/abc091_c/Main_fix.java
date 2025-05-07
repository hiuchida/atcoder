import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		List<Point> lr=new ArrayList<>();
		for (int i=0; i<n; i++) {
			int x=sc.nextInt();
			int y=sc.nextInt();
			lr.add(new Point(x, y));
		}
		List<Point> lb=new ArrayList<>();
		for (int i=0; i<n; i++) {
			int x=sc.nextInt();
			int y=sc.nextInt();
			lb.add(new Point(x, y));
		}
		Collections.sort(lr, Point.newComparator2());
		Collections.sort(lb, Point.newComparator1());
//		System.out.println(lb);
//		System.out.println(lr);
		int ans=0;
		for (Point pb : lb) {
			for (int i=0; i<lr.size(); i++) {
				Point pr=lr.get(i);
				if (pr.x<pb.x && pr.y<pb.y) {
					lr.remove(i);
					ans++;
//					System.out.println(pb+" "+pr);
					break;
				}
			}
		}
		System.out.println(ans);
	}
	static class Point implements Comparable<Point> {
		final int x;
		final int y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		static Comparator<Point> newComparator1() {
			return new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					int cmp = Integer.compare(o1.x, o2.x);
					return cmp;
				}
			};
		}
		static Comparator<Point> newComparator2() {
			return new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					int cmp = Integer.compare(o1.y, o2.y);
					return -cmp;
				}
			};
		}
		@Override
		public int compareTo(Point that) {
			int cmp = Integer.compare(this.x, that.x);
			if (cmp != 0) return cmp;
			return Integer.compare(this.y, that.y);
		}
		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}
		@Override
		public boolean equals(Object obj) {
			Point that = (Point) obj;
			return this.x == that.x && this.y == that.y;
		}
		@Override
		public String toString() {
			return "(" + x + "," + y + ")";
		}
	}
}
/*
3
2 0
3 1
1 3
4 2
0 4
5 5

3
0 0
1 1
5 2
2 3
3 4
4 5

2
2 2
3 3
0 0
1 1

5
0 0
7 3
2 2
4 8
1 6
8 5
6 9
5 4
9 1
3 7

5
0 0
1 1
5 5
6 6
7 7
2 2
3 3
4 4
8 8
9 9
*/
