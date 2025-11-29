import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static final boolean RELEASE = true;
	long calc(Dot d1, Dot d2) {
		return calc(d1.x, d1.y, d2.x, d2.y);
	}
	long calc(long x1, long y1, long x2, long y2) {
		long dx=Math.abs(x1-x2);
		dx=Math.min(l-dx, dx);
		long dy=Math.abs(y1-y2);
		dy=Math.min(l-dy, dy);
		double v=Math.sqrt(dx*dx+dy*dy);
		return Math.round(v);
	}
	void solve() {
		Scanner sc=new Scanner(System.in);
		init(sc);
//		boolean[] flag=new boolean[n];
		List<Pair> lp=new ArrayList<>();
		UnionFind uf=new UnionFind(n);
		long sum=0;
		for (int i=0; i<n; i++) {
			for (int j=i+1; j<n; j++) {
				lp.add(new Pair(i*n+j, calc(ad[i], ad[j])));
			}
		}
		Collections.sort(lp);
		Set<Integer> set=new TreeSet<>();
		for (int j=0; j<lp.size() && set.size()<m; j++) {
			Pair pair=lp.get(j);
			int i1=pair.st/n;
			int i2=pair.st%n;
			if (uf.same(i1, i2)) continue;
			if (uf.size(i1)+uf.size(i2)>k) continue;
			int r1=uf.root(i1);
			int r2=uf.root(i2);
			uf.merge(i1, i2);
			int r3=uf.root(i1);
			set.remove(r1);
			set.remove(r2);
			set.add(r3);
			System.out.println(0+" "+i1+" "+i2);
			sum+=pair.ed;
		}
		for (int j=0; j<lp.size(); j++) {
			Pair pair=lp.get(j);
			int i1=pair.st/n;
			int i2=pair.st%n;
			if (uf.same(i1, i2)) continue;
			if (uf.size(i1)+uf.size(i2)>k) continue;
			int r1=uf.root(i1);
			int r2=uf.root(i2);
			if (r1==r2) continue;
			if (set.contains(r1) && set.contains(r2)) continue;
			if (!set.contains(r1) && !set.contains(r2)) continue;
			uf.merge(i1, i2);
			int r3=uf.root(i1);
			set.remove(r1);
			set.remove(r2);
			set.add(r3);
			System.out.println(0+" "+i1+" "+i2);
			sum+=pair.ed;
		}
//		for (int i=0; i<n; i++) {
//			if (i==uf.root(i)) {
//				System.err.println(i+" "+uf.size(i));
//			}
//		}
		System.err.println("Cost:"+sum);
	}
	int n;
	int t;
	int m;
	int k;
	int l;
	Dot[] ad;
	void init(Scanner sc) {
		n=sc.nextInt();
		t=sc.nextInt();
		m=sc.nextInt();
		k=sc.nextInt();
		l=sc.nextInt();
		ad=new Dot[n];
		for (int i=0; i<n; i++) {
			int x=sc.nextInt();
			int y=sc.nextInt();
			int vx=sc.nextInt();
			int vy=sc.nextInt();
			ad[i]=new Dot(x, y, vx, vy);
		}
//		System.out.println(Arrays.toString(ad));
	}
	static Random rand=new Random(42);
	static long start;
	public static void main(String[] args) {
		start = System.currentTimeMillis();
		new Main().solve();
	}
	static class Dot {
		int x;
		int y;
		int vx;
		int vy;
		Dot(int x, int y, int vx, int vy) {
			this.x=x;
			this.y=y;
			this.vx=vx;
			this.vy=vy;
		}
		@Override
		public String toString() {
			return "(" + x + "," + y + "," + vx + "," + vy + ")";
		}
	}
	static class Pair implements Comparable<Pair> {
		final int st;
		final long ed;
		Pair(int st, long ed) {
			this.st = st;
			this.ed = ed;
		}
		static Comparator<Pair> newComparator1() {
			return new Comparator<Pair>() {
				@Override
				public int compare(Pair o1, Pair o2) {
					int cmp = Long.compare(o1.st, o2.st);
					return cmp;
				}
			};
		}
		static Comparator<Pair> newComparator2() {
			return new Comparator<Pair>() {
				@Override
				public int compare(Pair o1, Pair o2) {
					int cmp = Long.compare(o1.ed, o2.ed);
					return cmp;
				}
			};
		}
		@Override
		public int compareTo(Pair that) {
//			int cmp = Long.compare(this.st, that.st);
//			if (cmp != 0) return cmp;
			return Long.compare(this.ed, that.ed);
		}
		@Override
		public int hashCode() {
			return Objects.hash(st, ed);
		}
		@Override
		public boolean equals(Object obj) {
			Pair that = (Pair) obj;
			return this.st == that.st && this.ed == that.ed;
		}
		@Override
		public String toString() {
			return "(" + st + "," + ed + ")";
		}
	}
	static class UnionFind { //UnionFind20250102
		int[] uf;
		public UnionFind(int n) {
			uf = new int[n];
			for (int i=0; i<n; i++) uf[i] = -1;
		}
		public int root(int v) {
			if (uf[v] < 0) return v;
			uf[v] = root(uf[v]);
			return uf[v];
		}
		public void merge(int u, int v) {
			u = root(u);
			v = root(v);
			if (u == v) return;
			int uw = uf[u];
			int vw = uf[v];
			uf[u] += uf[v];
			uf[v] = u;
//			if (uw <= vw) {
//				uf[u] += uf[v];
//				uf[v] = u;
//			} else if (uw > vw) {
//				uf[v] += uf[u];
//				uf[u] = v;
//			}
		}
		public boolean same(int u, int v) {
			return root(u) == root(v);
		}
		public int size(int v) {
			v = root(v);
			return -uf[v];
		}
		@Override
		public String toString() {
			return Arrays.toString(uf);
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
}
/*



*/
