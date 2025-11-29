import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static final boolean RELEASE = true;
	void solve() {
//		for (int i=5; i<=20; i++) {
//			System.out.println((double)(i-1)/i*1e9);
//		}
		Scanner sc=new Scanner(System.in);
		init(sc);
		int ki=-1;
		int ai=-1;
		int bi=-1;
		double maxv=-1;
		for (int h=0; h<k; h++) {
			for (int i=0; i<n; i++) {
				for (int j=0; j<n; j++) {
					if (i==j) continue;
					double v1=add[h][i];
					double v2=add[h][j];
					double v=v1+(1-v2);
					if (maxv<v) {
						maxv=v;
						ki=h;
						ai=i;
						bi=j;
					}
					v=(1-v1)+v2;
					if (maxv<v) {
						maxv=v;
						ki=h;
						ai=j;
						bi=i;
					}
				}
			}
		}
		Answer ans=new Answer();
		for (int i=0; i<n; i++) {
			ans.ad[i]=i;
		}
		ans.s=n;
		ans.as[0]=new Sorter(ki, ai, bi);
		for (int i=1; i<m; i++) {
			ans.as[i]=new Sorter(-1);
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
	public static void main(String[] args) {
		start = System.currentTimeMillis();
		new Main().solve();
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
		if (Math.max(p1.x, p2.x)<Math.min(q1.x, q2.x)) return false;
		if (Math.max(q1.x, q2.x)<Math.min(p1.x, p2.x)) return false;
		if (Math.max(p1.y, p2.y)<Math.min(q1.y, q2.y)) return false;
		if (Math.max(q1.y, q2.y)<Math.min(p1.y, p2.y)) return false;
		int o1=orientation(p1, p2, q1);
		int o2=orientation(p1, p2, q2);
		int o3=orientation(q1, q2, p1);
		int o4=orientation(q1, q2, p2);
		return (o1*o2<=0)||(o3*o4<=0);
	}
}
/*



*/
