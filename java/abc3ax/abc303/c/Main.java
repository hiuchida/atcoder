import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		int h=sc.nextInt();
		int k=sc.nextInt();
		String s=sc.next();
		char[] ary=s.toCharArray();
		Set<Point> set=new HashSet<>();
		for (int i=0; i<m; i++) {
			int x=sc.nextInt();
			int y=sc.nextInt();
			set.add(new Point(x, y));
		}
		int x=0;
		int y=0;
		for (int i=0; i<n; i++) {
			h--;
			switch (ary[i]) {
			case 'R':
				x++;
				break;
			case 'L':
				x--;
				break;
			case 'U':
				y++;
				break;
			case 'D':
				y--;
				break;
			}
//			System.out.println(x+" "+y+" "+h);
			if (h<0) {
				System.out.println("No");
				System.exit(0);
			}
			Point p=new Point(x, y);
			if (set.contains(p)) {
				if (h<k) {
					h=k;
					set.remove(p);
				}
			}
		}
		System.out.println("Yes");
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
4 2 3 1
RUDL
-1 -1
1 0

5 2 1 5
LDRLD
0 0
-1 -1
*/
