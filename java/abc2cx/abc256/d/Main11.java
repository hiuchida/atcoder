import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		List<Point> list=new ArrayList<>();
		for (int i=0; i<n; i++) {
			list.add(new Point(sc.nextInt(), sc.nextInt()));
		}
		Collections.sort(list);
//		System.out.println(list);
//		Arrays.sort(ary);
//		System.out.println(Arrays.toString(ary));
		List<Point> ans=new ArrayList<>();
		while (list.size()>0) {
			int idx0=list.size()-1;
			Point p0=list.remove(idx0);
			int lt=p0.st;
			int rt=p0.ed;
			for (int i=idx0-1; i>=0 && list.get(i).ed>=p0.st; i--) {
				Point p1=list.remove(i);
				lt=Math.min(lt, p1.st);
			}
			ans.add(new Point(lt, rt));
//			System.out.println(ans);
		}
		Collections.sort(ans);
//		System.out.println(ans);
		for (Point p : ans) {
			System.out.println(p.st+" "+p.ed);
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
			int cmp = Integer.compare(this.ed, that.ed);
			if (cmp != 0) return cmp;
			return Integer.compare(this.st, that.st);
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
10 19
20 30
40 50
*/
