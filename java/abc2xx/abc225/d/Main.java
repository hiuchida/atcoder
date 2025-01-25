import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Point[] ary = new Point[n+1];
		for (int i=1; i<=n; i++) ary[i]=new Point(0, 0);
		int q = sc.nextInt();
		for (int i=0; i<q; i++) {
			int c = sc.nextInt();
			int x = sc.nextInt();
			int y;
			switch (c) {
			case 1:
				y = sc.nextInt();
				ary[x].ed=y;
				ary[y].st=x;
				break;
			case 2:
				y = sc.nextInt();
				ary[x].ed=0;
				ary[y].st=0;
				break;
			case 3:
				int head=x;
				while (ary[head].st != 0) head=ary[head].st;
				List<Integer> list = new ArrayList<>();
				while (ary[head].ed != 0) {
					list.add(head);
					head=ary[head].ed;
				}
				list.add(head);
				StringBuilder sb = new StringBuilder();
				sb.append(list.size());
				for (int v : list) {
					sb.append(" ").append(v);
				}
				System.out.println(sb);
				break;
			}
		}
	}
	static class Point implements Comparable<Point> {
		int st;
		int ed;
		Point(int st, int ed) {
			this.st = st;
			this.ed = ed;
		}
		@Override
		public int compareTo(Main.Point that) {
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
7 14
1 6 3
1 4 1
1 5 2
1 2 7
1 3 5
3 2
3 4
3 6
2 3 5
2 4 1
1 1 5
3 2
3 4
3 6
*/
