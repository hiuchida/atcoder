import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static final boolean RELEASE = true;
	static final int N=1000;
	static final Point og=new Point(400, 400);
	static Rectangle[] ar;
	static boolean[] flag;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		ar=new Rectangle[N];
		flag=new boolean[N];
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
		Point p0=og;
		Point pn=og;
		for (int i=0; i<n/2; i++) {
			int minist=minvst(p0);
			ans.ai[i]=minist;
			flag[minist]=true;
			p0=ar[minist].ed;
			int mined=minved(pn);
			ans.ai[n-1-i]=mined;
			flag[mined]=true;
			pn=ar[mined].st;
		}
		int idx=0;
		ans.ap[idx++]=og;
		for (int i=0; i<n; i++) {
			ans.ap[idx++]=ar[ans.ai[i]].st;
			ans.ap[idx++]=ar[ans.ai[i]].ed;
		}
		ans.ap[idx++]=og;
		ans.print();
		if (!RELEASE) System.err.println("score="+ans.score+" length="+ans.length);
	}
	static int minvst(Point p0) {
		int minv=Integer.MAX_VALUE;
		int mini=-1;
		for (int i=0; i<N; i++) {
			if (flag[i]) continue;
			int v=calc(p0, ar[i].st)+ar[i].length;
			if (minv>v) {
				minv=v;
				mini=i;
			}
		}
		return mini;
	}
	static int minved(Point p0) {
		int minv=Integer.MAX_VALUE;
		int mini=-1;
		for (int i=0; i<N; i++) {
			if (flag[i]) continue;
			int v=calc(p0, ar[i].ed)+ar[i].length;
			if (minv>v) {
				minv=v;
				mini=i;
			}
		}
		return mini;
	}
	static class Answer {
		int m;
		int[] ai;
		int n;
		Point[] ap;
		int length;
		long score;
		Answer(int m) {
			this.m=m;
			this.ai=new int[m];
			this.n=(m+1)*2;
			this.ap=new Point[n];
		}
		void print() {
			StringBuilder sb1=new StringBuilder();
			sb1.append(m);
			for (int i=0; i<m; i++) {
				sb1.append(" "+(ai[i]+1));
			}
			
			StringBuilder sb2=new StringBuilder();
			this.length=0;
			sb2.append(n);
			Point p0=ap[0];
			for (int i=0; i<n; i++) {
				Point p1=ap[i];
				sb2.append(" "+p1.y+" "+p1.x);
				length+=calc(p0, p1);
				p0=p1;
			}

			score=(long)(10*10000*10000/(1000+length));
			score=(score+5)/10;

			System.out.println(sb1.toString());
			System.out.println(sb2.toString());
		}
	}
	static class Rectangle {
		final Point st;
		final Point ed;
		final int length;
		Rectangle(Point st, Point ed) {
			this.st=st;
			this.ed=ed;
			this.length=calc(st.y, st.x, ed.y, ed.x);
		}
		Rectangle(int y0, int x0, int y1, int x1) {
			this(new Point(y0, x0), new Point(y1, x1));
		}
		@Override
		public String toString() {
			return "(" + length + ":" + st.y + "," + st.x + ":" + ed.y + "," + ed.x + ")";
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
	//abc035_b,abc057_b,abc086_c,abc295_b: x1,y1からx2,y2までのマンハッタン距離
	static int calc(Point p1, Point p2) {
		return calc(p1.y, p1.x, p2.y, p2.x);
	}
	static int calc(int x1, int y1, int x2, int y2) {
		int dx=x1-x2;
		int dy=y1-y2;
		return Math.abs(dx)+Math.abs(dy);
	}
}
/*



*/
