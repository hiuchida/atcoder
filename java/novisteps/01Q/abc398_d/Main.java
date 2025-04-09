import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int r = sc.nextInt();
		int c = sc.nextInt();
		String s = sc.next();
		TreeSet<Point> set=new TreeSet<>();
		int dy=0;
		int dx=0;
		StringBuilder sb=new StringBuilder();
		set.add(new Point(-dy, -dx));
		for (int i=0; i<n; i++) {
			char ch=s.charAt(i);
			switch (ch) {
			case 'N':
				dy--;
				break;
			case 'S':
				dy++;
				break;
			case 'W':
				dx--;
				break;
			case 'E':
				dx++;
				break;
			}
			set.add(new Point(-dy, -dx));
			if (set.contains(new Point(r-dy, c-dx))) {
				sb.append(1);
			} else {
				sb.append(0);
			}
		}
		System.out.println(sb);
	}
	static class Point implements Comparable<Point> {
		final int y;
		final int x;
		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
		static Comparator<Point> newComparator1() {
			return new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					int cmp = Integer.compare(o1.y, o2.y);
					return cmp;
				}
			};
		}
		static Comparator<Point> newComparator2() {
			return new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					int cmp = Integer.compare(o1.x, o2.x);
					return cmp;
				}
			};
		}
		@Override
		public int compareTo(Point that) {
			int cmp = Integer.compare(this.y, that.y);
			if (cmp != 0) return cmp;
			return Integer.compare(this.x, that.x);
		}
		@Override
		public int hashCode() {
			return Objects.hash(y, x);
		}
		@Override
		public boolean equals(Object obj) {
			Point that = (Point) obj;
			return this.y == that.y && this.x == that.x;
		}
		@Override
		public String toString() {
			return "(" + y + "," + x + ")";
		}
	}
}
/*
6 -2 1
NNEEWS

10 1 2
NEESESWEES

20 -1 -2
WWNNWSWEWNSWWENSNWWN
*/
