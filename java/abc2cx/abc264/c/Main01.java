import java.util.*;
public class Main {
	static int h1;
	static int w1;
	static int[][] ary1;
	static int h2;
	static int w2;
	static int[][] ary2;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		h1 = sc.nextInt();
		w1 = sc.nextInt();
		ary1=new int[h1][w1];
		for (int i=0; i<h1; i++) {
			for (int j=0; j<w1; j++) {
				ary1[i][j]=sc.nextInt();
			}
		}
		h2 = sc.nextInt();
		w2 = sc.nextInt();
		ary2=new int[h2][w2];
		for (int i=0; i<h2; i++) {
			for (int j=0; j<w2; j++) {
				ary2[i][j]=sc.nextInt();
			}
		}
		if (h1<h2 || w1<w2) {
			System.out.println("No");
			System.exit(0);
		}
		for (int i=0; i<h1; i++) {
			System.out.println(Arrays.toString(ary1[i]));
		}
		for (int i=0; i<h2; i++) {
			System.out.println(Arrays.toString(ary2[i]));
		}
		TreeSet<Point> set1=new TreeSet<>();
		for (int i=0; i<h1; i++) {
			for (int j=0; j<w1; j++) {
				if (ary1[i][j]==ary2[0][0]) set1.add(new Point(i, j));
			}
		}
		TreeSet<Point> set2=new TreeSet<>();
		for (int i=0; i<h1; i++) {
			for (int j=0; j<w1; j++) {
				if (ary1[i][j]==ary2[h2-1][w2-1]) set2.add(new Point(i, j));
			}
		}
		System.out.println(set1);
		System.out.println(set2);
		if (set1.size()==0 || set2.size()==0) {
			System.out.println("No");
			System.exit(0);
		}
		for (Point p1 : set1) {
			for (Point p2 : set2) {
				if (p1.y>p2.y || p1.x>p2.x) continue;
				System.out.println(p1+" "+p2);
				check(p1, p2);
			}
		}
		System.out.println("No");
	}
	static void check(Point p1, Point p2) {
		int j=p1.y;
		for (int i=0; i<h2; i++) {
			int v=ary2[i][0];
			for (; j<=p2.y; j++) {
				if (v==ary1[j][p1.x]) {
					j++;
					break;
				}
			}
		}
		for (int j=0; j<w2; j++) {
		}
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
4 5
1 2 3 4 5
6 7 8 9 10
11 12 13 14 15
16 17 18 19 20
2 3
6 8 9
16 18 19

3 3
1 1 1
1 1 1
1 1 1
1 1
2
*/
/*
3 3
1 1 1
1 1 1
1 1 1
1 1
1
*/
