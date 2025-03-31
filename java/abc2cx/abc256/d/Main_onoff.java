import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		List<Point> list=new ArrayList<>();
		for (int i=0; i<n; i++) {
			list.add(new Point(sc.nextInt(), 0));
			list.add(new Point(sc.nextInt(), 1));
		}
		Collections.sort(list);
		System.out.println(list);
		List<Point> ans=new ArrayList<>();
		int cnt=0;
		int lt=0;
		int rt=0;
		for (Point p : list) {
			if (p.ed==0) {
				if (cnt==0) lt=p.st;
				cnt++;
			} else {
				cnt--;
				if (cnt==0) {
					rt=p.st;
					ans.add(new Point(lt, rt));
				}
			}
		}
		for (Point p : ans) {
			System.out.println(p.st+" "+p.ed);
		}
	}
	static class Point implements Comparable<Point> {
		int st;
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
3
10 20
20 30
40 50

3
10 40
30 60
20 50
*/
/*
3
10 20
20 40
40 50

3
10 19
20 30
40 50
*/
