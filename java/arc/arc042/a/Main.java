import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		Point[] ary = new Point[n+1];
		for (int i=1; i<=n; i++) {
			ary[i]=new Point(0,0);
		}
		int head=1;
		for (int i=1; i<=n; i++) {
			if (i-1>=1) ary[i-1].ed=i;
			ary[i].st=i-1;
		}
//		System.out.println(Arrays.toString(ary));
		for (int i=0; i<m; i++) {
			int a = sc.nextInt();
			if (head==a) continue;
			int prev=ary[a].st;
			int next=ary[a].ed;
			if (prev!=0) ary[prev].ed=next;
			if (next!=0) ary[next].st=prev;
			ary[head].st=a;
			ary[a].ed=head;
			ary[a].st=0;
			head=a;
//			System.out.println(Arrays.toString(ary));
		}
		int cur=head;
		while (cur != 0) {
			System.out.println(cur);
			cur=ary[cur].ed;
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
3 3
2
3
1

3 3
1
1
1

10 10
3
1
4
1
5
9
2
6
5
3
*/
