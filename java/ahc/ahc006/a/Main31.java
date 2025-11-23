import java.awt.Point;
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
//		for (int i=0; i<N; i++) {
//			int v1=calc(og, ar[i].st);
//			int v2=calc(og, ar[i].ed);
//			if (v1>400 || v2>400) flag[i]=true;
//		}
		int n=50;
		Answer ans=new Answer(n);
		for (int i=0; i<n; i++) {
			int minist=minvsted(og);
			ans.ai[i]=minist;
			flag[minist]=true;
		}
		int idx=0;
		ans.ap[idx++]=og;
		for (int i=0; i<n; i++) {
			ans.ap[idx++]=ar[ans.ai[i]].st;
			ans.ap[idx++]=ar[ans.ai[i]].ed;
		}
		ans.ap[idx++]=og;
		ans.print();
//		int idx=0;
//		ans.ap[idx++]=og;
//		p0=og;
//		Set<Point> set=new TreeSet<>();
//		Map<Point,Integer> map=new TreeMap<>();
//		for (int i=0; i<n; i++) {
//			set.add(ar[ans.ai[i]].st);
//			map.put(ar[ans.ai[i]].st, i);
//		}
//		while (set.size()>0) {
//			Point pi=srch(set, p0);
//			ans.ap[idx++]=pi;
//			Integer i=map.get(pi);
//			if (i!=null) {
//				set.remove(pi);
//				set.add(ar[ans.ai[i]].ed);
//				map.remove(pi);
//			} else {
//				set.remove(pi);
//			}
//			p0=pi;
//		}
//		ans.ap[idx++]=og;
//		ans.print();
		if (!RELEASE) System.err.println("score="+ans.score+" length="+ans.length);
	}
	static Point srch(Set<Point> set, Point p0) {
		int minv=Integer.MAX_VALUE;
		Point minp=null;
		for (Point p1 : set) {
			int v=calc(p0, p1);
			if (minv>v) {
				minv=v;
				minp=p1;
			}
		}
		return minp;
	}
	static int minvsted(Point p0) {
		int minv=Integer.MAX_VALUE;
		int mini=-1;
		for (int i=0; i<N; i++) {
			if (flag[i]) continue;
			int v1=calc(p0, ar[i].st);
			int v2=calc(p0, ar[i].ed);
			int v=v1+v2;
			if (minv>v) {
				minv=v;
				mini=i;
			}
		}
		return mini;
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
	static int minvsted(Point p0, Point pn) {
		int minv=Integer.MAX_VALUE;
		int mini1=-1;
		int mini2=-1;
		for (int i=0; i<N; i++) {
			if (flag[i]) continue;
			int v1=calc(p0, ar[i].st)+ar[i].length;
			if (v1>minv) continue;
			for (int j=0; j<N; j++) {
				if (flag[j]) continue;
				int v2=calc(pn, ar[j].ed)+ar[j].length;
				if (v1+v2>minv) continue;
				int v3=calc(ar[i].ed, ar[j].st);
				int v=v1+v2+v3;
				if (minv>v) {
					minv=v;
					mini1=i;
					mini2=j;
				}
			}
		}
		return mini1*N+mini2;
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
