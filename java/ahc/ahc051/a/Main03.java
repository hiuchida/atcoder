import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static final boolean RELEASE = true;
	boolean isCross(List<Line> ll) {
		for (int i=0; i<ll.size()-1; i++) {
			for (int j=i+1; j<ll.size(); j++) {
				if (ll.get(i).isIntersect(ll.get(j))) {
					System.out.println("debug "+ll.get(i)+" "+ll.get(j)+" "+i+" "+j);
					return true;
				}
			}
		}
		return false;
	}
	void solve() {
		Scanner sc=new Scanner(System.in);
		init(sc);
//		for (int i=5; i<=20; i++) {
//			System.out.println((double)(i-1)/i*1e9);
//		}
//		List<Line> ll=new ArrayList<>();
//		ll.add(new Line(og, aps[0]));
//		ll.add(new Line(aps[0], aps[3]));
//		ll.add(new Line(aps[0], aps[2]));
//		ll.add(new Line(aps[3], apd[4]));
//		ll.add(new Line(aps[3], apd[3]));
//		ll.add(new Line(aps[2], apd[2]));
//		ll.add(new Line(aps[2], apd[1]));
//		for (int i=0; i<ll.size(); i++) {
//			System.out.println("line "+ll.get(i)+" "+i);
//		}
//		for (int i=0; i<ll.size()-1; i++) {
//			for (int j=i+1; j<ll.size(); j++) {
//				if (ll.get(i).isIntersect(ll.get(j))) {
//					System.out.println("debug "+ll.get(i)+" "+ll.get(j)+" "+i+" "+j);
//				}
//			}
//		}
		int pi=-1;
		int ki=-1;
		int ai=-1;
		int bi=-1;
		double maxv=-1;
        int iteration = 0;
		while (true) {
			if (isTimeout()) break;
			List<Line> ll=new ArrayList<>();
			int p=rand.nextInt(m);
			int h=rand.nextInt(k);
			int i=rand.nextInt(n);
			int j=rand.nextInt(n);
			if (i==j) continue;
			ll.add(new Line(og, aps[p]));
			ll.add(new Line(aps[p], aps[i]));
			ll.add(new Line(aps[p], aps[j]));
			if (isCross(ll)) continue;
			double v1=add[h][i];
			double v2=add[h][j];
			double v=v1+(1-v2);
			if (maxv<v) {
				maxv=v;
				pi=p;
				ki=h;
				ai=i;
				bi=j;
				System.err.println("iteration: " + iteration + ", maxv: " + maxv+" A:"+h+" "+i+" "+j);
			}
			v=(1-v1)+v2;
			if (maxv<v) {
				maxv=v;
				pi=p;
				ki=h;
				ai=j;
				bi=i;
				System.err.println("iteration: " + iteration + ", maxv: " + maxv+" B:"+h+" "+i+" "+j);
			}
			iteration++;
		}
//		for (int h=0; h<k; h++) {
//			for (int i=0; i<n; i++) {
//				for (int j=0; j<n; j++) {
//					if (i==j) continue;
//					double v1=add[h][i];
//					double v2=add[h][j];
//					double v=v1+(1-v2);
//					if (maxv<v) {
//						maxv=v;
//						ki=h;
//						ai=i;
//						bi=j;
//					}
//					v=(1-v1)+v2;
//					if (maxv<v) {
//						maxv=v;
//						ki=h;
//						ai=j;
//						bi=i;
//					}
//				}
//			}
//		}
		Answer ans=new Answer();
		for (int i=0; i<n; i++) {
			ans.ad[i]=i;
		}
		ans.s=n+pi;
		for (int i=0; i<m; i++) {
			if (i==pi) ans.as[i]=new Sorter(ki, ai, bi);
			else ans.as[i]=new Sorter(-1);
		}
		ans.print();
		double scored=1e9*(n-maxv)/n;
		long score=Math.round(scored);
		if (!RELEASE) System.err.println("score="+score+" ,maxv="+maxv);
	}
	class Sorter {
		int k;
		int v1;
		int v2;
		Sorter(int k) {
			this.k=k;
		}
		Sorter(int k, int v1, int v2) {
			this.k=k;
			this.v1=v1;
			this.v2=v2;
		}
		void print() {
			if (k>=0) System.out.println(k+" "+v1+" "+v2);
			else System.out.println("-1");
		}
	}
	class Answer {
		int[] ad;
		int s;
		Sorter[] as;
		Answer() {
			ad=new int[n];
			as=new Sorter[m];
		}
		void print() {
			for (int i=0; i<n; i++) {
				if (i>0) System.out.print(" ");
				System.out.print(ad[i]);
			}
			System.out.println();
			System.out.println(s);
			for (int i=0; i<m; i++) {
				as[i].print();
			}
		}
	}
	final Point og=new Point(0, 5000);
	int n;
	int m;
	int k;
	Point[] apd;
	Point[] aps;
	double[][] add;
	void init(Scanner sc) {
		n=sc.nextInt();
		m=sc.nextInt();
		k=sc.nextInt();
		apd=new Point[n];
		for (int i=0; i<n; i++) {
			int x=sc.nextInt();
			int y=sc.nextInt();
			apd[i]=new Point(x, y);
		}
//		System.out.println(Arrays.toString(apd));
		aps=new Point[m];
		for (int i=0; i<m; i++) {
			int x=sc.nextInt();
			int y=sc.nextInt();
			aps[i]=new Point(x, y);
		}
//		System.out.println(Arrays.toString(aps));
		add=new double[k][n];
		for (int i=0; i<k; i++) {
			for (int j=0; j<n; j++) {
				add[i][j]=sc.nextDouble();
			}
		}
//		for (int i=0; i<k; i++) {
//			System.out.println(Arrays.toString(add[i]));
//		}
	}
	static Random rand=new Random(42);
	static long start;
	static final int time_limit = 1700;
	static boolean isTimeout() {
		long lap = System.currentTimeMillis();
		long elaps = lap - start;
        return elaps >= time_limit;
	}
	public static void main(String[] args) {
		start = System.currentTimeMillis();
		new Main().solve();
	}
	static class Line {
		Point st;
		Point ed;
		Line(Point st, Point ed) {
			this.st=st;
			this.ed=ed;
		}
		boolean isIntersect(Line that) {
			return segment_intersect(this.st, this.ed, that.st, that.ed);
		}
		@Override
		public String toString() {
			return "(" + st + "-" + ed + ")";
		}
	}
	static class Point implements Comparable<Point> {
		final int x;
		final int y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		static Comparator<Point> newComparator1() {
			return new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					int cmp = Integer.compare(o1.x, o2.x);
					return cmp;
				}
			};
		}
		static Comparator<Point> newComparator2() {
			return new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					int cmp = Integer.compare(o1.y, o2.y);
					return cmp;
				}
			};
		}
		@Override
		public int compareTo(Point that) {
			int cmp = Integer.compare(this.x, that.x);
			if (cmp != 0) return cmp;
			return Integer.compare(this.y, that.y);
		}
		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}
		@Override
		public boolean equals(Object obj) {
			Point that = (Point) obj;
			return this.x == that.x && this.y == that.y;
		}
		@Override
		public String toString() {
			return "(" + x + "," + y + ")";
		}
	}
	//符号を返す
	static int sign(double v) {
		if (v<0) return -1;
		else if (v>0) return 1;
		return 0;
	}
	//外積(z軸)の向きを返す
	static int orientation(Point a, Point b, Point c) {
		double cross=(b.x-a.x)*(c.y-a.y)-(b.y-a.y)*(c.x-a.x);
		return sign(cross);
	}
	//線分の交差判定
	static boolean segment_intersect(Point p1, Point p2, Point q1, Point q2) {
		if (p1.equals(q1) || p1.equals(q2)) return false;
		if (p2.equals(q1) || p2.equals(q2)) return false;
		if (Math.max(p1.x, p2.x)<Math.min(q1.x, q2.x)) return false;
		if (Math.max(q1.x, q2.x)<Math.min(p1.x, p2.x)) return false;
		if (Math.max(p1.y, p2.y)<Math.min(q1.y, q2.y)) return false;
		if (Math.max(q1.y, q2.y)<Math.min(p1.y, p2.y)) return false;
		int o1=orientation(p1, p2, q1);
		int o2=orientation(p1, p2, q2);
		int o3=orientation(q1, q2, p1);
		int o4=orientation(q1, q2, p2);
		return (o1*o2<=0)&&(o3*o4<=0);
	}
}
/*



*/
