import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=9;
		List<Point> list=new ArrayList<>();
		for (int i=0; i<n; i++) {
			String s=sc.next();
			for (int j=0; j<n; j++) {
				if (s.charAt(j)=='#') {
					list.add(new Point(j+1, i+1));
				}
			}
		}
//		System.out.println(list);
		int ans=0;
		for (int a=0; a<list.size(); a++) {
			for (int b=a+1; b<list.size(); b++) {
				for (int c=b+1; c<list.size(); c++) {
					for (int d=c+1; d<list.size(); d++) {
						ans+=check(list, a, b, c, d);
					}
				}
			}
		}
		System.out.println(ans);
	}
	static int check(List<Point> list, int a, int b, int c, int d) {
		Point[] ary=new Point[4];
		ary[0]=list.get(a);
		ary[1]=list.get(b);
		ary[2]=list.get(c);
		ary[3]=list.get(d);
		Arrays.sort(ary);
		long[] da=new long[3];
		for (int i=0; i<3; i++) {
			da[i]=calc(ary[0], ary[i+1]);
		}
//		Arrays.sort(da);
		long[] dd=new long[3];
		for (int i=0; i<3; i++) {
			dd[i]=calc(ary[3], ary[i]);
		}
//		Arrays.sort(dd);
//		for (int i=0; i<3; i++) {
//			if (da[i]!=dd[i]) return 0;
//		}
//		if (da[0]!=da[1] && da[1]!=da[2]) return 0;
		if (da[0]!=da[1]) return 0;
		if (da[0]!=dd[2]) return 0;
		if (dd[1]!=dd[2]) return 0;
//		System.out.println(Arrays.toString(ary));
//		System.out.println(Arrays.toString(da));
//		System.out.println(Arrays.toString(dd));
		return 1;
	}
	static long calc(Point p1, Point p2) {
		long dx=p1.x-p2.x;
		long dy=p1.y-p2.y;
		return dx*dx+dy*dy;
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
					return cmp;
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
##.......
##.......
.........
.......#.
.....#...
........#
......#..
.........
.........

.#.......
#.#......
.#.......
.........
....#.#.#
.........
....#.#.#
........#
.........
*/
