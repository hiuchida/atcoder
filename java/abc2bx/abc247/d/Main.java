import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int q = sc.nextInt();
		Deque<Point> que=new ArrayDeque<>();
		for (int i=0; i<q; i++) {
			int cmd = sc.nextInt();
			if (cmd==1) {
				int x = sc.nextInt();
				int c = sc.nextInt();
				que.addLast(new Point(x, c));
//				System.out.println(que);
			} else {
				int c = sc.nextInt();
				long ans=0;
				while (c>0) {
					Point p=que.removeFirst();
					if (c<p.ed) {
						ans+=(long)c*p.st;
						int cc=p.ed-c;
						que.addFirst(new Point(p.st, cc));
						c=0;
					} else {
						ans+=(long)p.ed*p.st;
						c-=p.ed;
					}
				}
				System.out.println(ans);
			}
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
4
1 2 3
2 2
1 3 4
2 3

2
1 1000000000 1000000000
2 1000000000

5
1 1 1
1 1 1
1 1 1
1 1 1
1 1 1
*/
