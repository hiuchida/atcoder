import java.util.*;
public class Main {
	static final int N=1000;
	static Rectangle[] ar;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		ar=new Rectangle[N];
		for (int i=0; i<N; i++) {
			int a=sc.nextInt();
			int b=sc.nextInt();
			int c=sc.nextInt();
			int d=sc.nextInt();
			ar[i]=new Rectangle(a, b, c, d);
		}
//		System.out.println(Arrays.toString(ar));
		int n=50;
		Answer ans=new Answer(n);
		for (int i=0; i<n; i++) {
			ans.ary[i]=i;
		}
		ans.print();
	}
	static class Answer {
		int m;
		int[] ary;
		Answer(int m) {
			this.m=m;
			this.ary=new int[m];
		}
		void print() {
			System.out.print(m);
			for (int i=0; i<m; i++) {
				System.out.print(" "+(ary[i]+1));
			}
			System.out.println();
			int n=(m+1)*2;
			System.out.print(n);
			System.out.print(" "+400+" "+400);
			for (int i=0; i<m; i++) {
				Point st=ar[ary[i]].st;
				Point ed=ar[ary[i]].ed;
				System.out.print(" "+st.y+" "+st.x+" "+ed.y+" "+ed.x);
			}
			System.out.print(" "+400+" "+400);
			System.out.println();
		}
	}
	static class Rectangle {
		final Point st;
		final Point ed;
		Rectangle(Point st, Point ed) {
			this.st=st;
			this.ed=ed;
		}
		Rectangle(int y0, int x0, int y1, int x1) {
			this.st=new Point(y0, x0);
			this.ed=new Point(y1, x1);
		}
		@Override
		public String toString() {
			return "(" + st.y + "," + st.x + ":" + ed.y + "," + ed.x + ")";
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



*/
