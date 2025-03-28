import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		List<Point> list=new ArrayList<>();
		int ans=0;
		for (int i=0; i<n; i++) {
			if (list.size()==0) {
				list.add(new Point(ary[i], 1));
				ans++;
			} else {
				Point p=list.get(list.size()-1);
				if (p.st==ary[i]) {
					p.ed++;
					ans++;
					if (p.st==p.ed) {
						list.remove(list.size()-1);
						ans-=p.st;
					}
				} else {
					list.add(new Point(ary[i], 1));
					ans++;
				}
			}
			System.out.println(ans);
		}
	}
	static class Point implements Comparable<Point> {
		final int st;
		int ed;
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
5
3 2 3 2 2

10
2 3 2 3 3 3 2 3 3 2
*/
