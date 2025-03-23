import java.util.*;
public class Main {
	static TreeSet<Point> setab;
	static TreeSet<Point> setcd;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		setab=new TreeSet<>();
		for (int i=0; i<m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			setab.add(new Point(a, b));
		}
//		System.out.println(setab);
		setcd=new TreeSet<>();
		for (int i=0; i<m; i++) {
			int c = sc.nextInt();
			int d = sc.nextInt();
			setcd.add(new Point(c, d));
		}
//		System.out.println(setcd);
		int[] ary=new int[n];
		boolean[] flag=new boolean[n];
		dfs(ary, 0, flag);
		System.out.println("No");
	}
	static void dfs(int[] ary, int i, boolean[] flag) {
		if (ary.length==i) {
//			System.out.println(Arrays.toString(ary));
			check(ary);
		}
		for (int j=1; j<=ary.length; j++) {
			if (flag[j-1]) continue;
			flag[j-1]=true;
			ary[i]=j;
			dfs(ary, i+1, flag);
			flag[j-1]=false;
		}
	}
	static void check(int[] ary) {
		TreeSet<Point> set=new TreeSet<>();
		for (Point p : setcd) {
			int c=ary[p.st-1];
			int d=ary[p.ed-1];
			if (c<d) set.add(new Point(c, d));
			else set.add(new Point(d, c));
		}
//		System.out.println(set+" "+Arrays.toString(ary));
		if (setab.equals(set)) {
			System.out.println("Yes");
			System.exit(0);
		}
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
4 4
1 2
1 3
1 4
3 4
1 3
1 4
2 3
3 4

5 6
1 2
1 3
1 4
3 4
3 5
4 5
1 2
1 3
1 4
1 5
3 5
4 5

8 0
*/
