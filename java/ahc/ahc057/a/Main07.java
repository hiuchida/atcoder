import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static final boolean RELEASE = true;
	long calc(Dot d1, Dot d2) {
		return calc(d1.x, d1.y, d2.x, d2.y);
	}
	long calc(double x1, double y1, double x2, double y2) {
		double dx=Math.abs(x1-x2);
		dx=Math.min(l-dx, dx);
		double dy=Math.abs(y1-y2);
		dy=Math.min(l-dy, dy);
		double v=Math.sqrt(dx*dx+dy*dy);
		return Math.round(v);
	}
	class TimeShift {
//		final int ratio=25;
		final int ratio=10;
//		final int ratio=50;
		UnionFind uf=new UnionFind(n);
		Set<Integer> set=new TreeSet<>();
		int tt;
		List<Pair> lp=new ArrayList<>();
		List<String> ls=new ArrayList<>();
		Set<Point> ans=new HashSet<>();
		long sum=0;
		TimeShift(int tt, UnionFind uf, Set<Integer> set, Set<Point> ans) {
			this.uf=uf;
			this.tt=tt;
			if (set!=null && ans!=null) {
				this.set=new TreeSet<>(set);
//				for (Point pt : ans) {
//					if (set.contains(pt.x)) uf.merge(pt.x, pt.y);
//					else uf.merge(pt.y, pt.x);
//				}
			}
			for (int i=0; i<n; i++) {
				for (int j=i+1; j<n; j++) {
					lp.add(new Pair(i*n+j, calc(ad[i], ad[j])));
				}
			}
			Collections.sort(lp);
		}
		void play1st() {
			for (int j=0; j<lp.size() && set.size()<m; j++) {
				Pair pair=lp.get(j);
				int i1=pair.st/n;
				int i2=pair.st%n;
				if (ans.contains(new Point(i1, i2))) continue;
				if (uf.same(i1, i2)) continue;
				if (uf.size(i1)+uf.size(i2)>k) continue;
				int r1=uf.root(i1);
				int r2=uf.root(i2);
				int s1=uf.size(i1);
				int s2=uf.size(i2);
				uf.merge(i1, i2);
				int r3=uf.root(i1);
				join(r1, r2, r3, s1, s2);
				set.remove(r1);
				set.remove(r2);
				set.add(r3);
				ls.add(tt+" "+i1+" "+i2);
				ans.add(new Point(i1, i2));
				sum+=pair.ed;
			}
			for (int j=0; j<lp.size(); j++) {
				Pair pair=lp.get(j);
				if (pair.ed>l/ratio) break;
				int i1=pair.st/n;
				int i2=pair.st%n;
				if (ans.contains(new Point(i1, i2))) continue;
				if (uf.same(i1, i2)) continue;
				if (uf.size(i1)+uf.size(i2)>k) continue;
				int r1=uf.root(i1);
				int r2=uf.root(i2);
				int s1=uf.size(i1);
				int s2=uf.size(i2);
				if (r1==r2) continue;
				if (set.contains(r1) && set.contains(r2)) continue;
				if (!set.contains(r1) && !set.contains(r2)) continue;
				uf.merge(i1, i2);
				int r3=uf.root(i1);
				join(r1, r2, r3, s1, s2);
				set.remove(r1);
				set.remove(r2);
				set.add(r3);
				ls.add(tt+" "+i1+" "+i2);
				ans.add(new Point(i1, i2));
				sum+=pair.ed;
			}
		}
		void play2nd() {
			for (int j=0; j<lp.size(); j++) {
				Pair pair=lp.get(j);
				if (pair.ed>l/ratio) break;
				int i1=pair.st/n;
				int i2=pair.st%n;
				if (ans.contains(new Point(i1, i2))) continue;
				if (uf.same(i1, i2)) continue;
				if (uf.size(i1)+uf.size(i2)>k) continue;
				int r1=uf.root(i1);
				int r2=uf.root(i2);
				int s1=uf.size(i1);
				int s2=uf.size(i2);
				if (r1==r2) continue;
				if (set.contains(r1) && set.contains(r2)) continue;
				if (!set.contains(r1) && !set.contains(r2)) continue;
				uf.merge(i1, i2);
				int r3=uf.root(i1);
				join(r1, r2, r3, s1, s2);
				set.remove(r1);
				set.remove(r2);
				set.add(r3);
				ls.add(tt+" "+i1+" "+i2);
				ans.add(new Point(i1, i2));
				sum+=pair.ed;
			}
		}
		void play() {
			for (int j=0; j<lp.size() && set.size()<m; j++) {
				Pair pair=lp.get(j);
				int i1=pair.st/n;
				int i2=pair.st%n;
				if (ans.contains(new Point(i1, i2))) continue;
				if (uf.same(i1, i2)) continue;
				if (uf.size(i1)+uf.size(i2)>k) continue;
				int r1=uf.root(i1);
				int r2=uf.root(i2);
				int s1=uf.size(i1);
				int s2=uf.size(i2);
				uf.merge(i1, i2);
				int r3=uf.root(i1);
//				join(r1, r2, r3, s1, s2);
				set.remove(r1);
				set.remove(r2);
				set.add(r3);
				ls.add(tt+" "+i1+" "+i2);
				sum+=pair.ed;
			}
			for (int j=0; j<lp.size(); j++) {
				Pair pair=lp.get(j);
				int i1=pair.st/n;
				int i2=pair.st%n;
				if (ans.contains(new Point(i1, i2))) continue;
				if (uf.same(i1, i2)) continue;
				if (uf.size(i1)+uf.size(i2)>k) continue;
				int r1=uf.root(i1);
				int r2=uf.root(i2);
				int s1=uf.size(i1);
				int s2=uf.size(i2);
				if (r1==r2) continue;
				if (set.contains(r1) && set.contains(r2)) continue;
				if (!set.contains(r1) && !set.contains(r2)) continue;
				uf.merge(i1, i2);
				int r3=uf.root(i1);
//				join(r1, r2, r3, s1, s2);
				set.remove(r1);
				set.remove(r2);
				set.add(r3);
				ls.add(tt+" "+i1+" "+i2);
				sum+=pair.ed;
			}
		}
	}
	void join(int r1, int r2, int r3, int s1, int s2) {
		double vx1=s1*ad[r1].vx;
		double vx2=s2*ad[r2].vx;
		double vy1=s1*ad[r1].vy;
		double vy2=s2*ad[r2].vy;
		ad[r3].vx=(vx1+vx2)/(s1+s2);
		ad[r3].vy=(vy1+vy2)/(s1+s2);
	}
	void moveAll(UnionFind uf, int step) {
		for (int i=0; i<n; i++) {
			int r=uf.root(i);
			double x=ad[i].x;
			double y=ad[i].y;
			x+=ad[r].vx*step;
			y+=ad[r].vy*step;
			x-=l*step;
			y-=l*step;
			while (x<0) x+=l;
			while (y<0) y+=l;
			ad[i].x=x;
			ad[i].y=y;
		}
	}
	void solve() {
		Scanner sc=new Scanner(System.in);
		init(sc);
//		boolean[] flag=new boolean[n];
		TimeShift[] ts=new TimeShift[t];
		int step=10;
		UnionFind uf=new UnionFind(n);
		Set<Integer> set=new TreeSet<>();
		Set<Point> ans=new HashSet<>();
		ts[0]=new TimeShift(0, uf, set, ans);
		ts[0].play1st();
		uf=ts[0].uf;
		set=ts[0].set;
		ans=ts[0].ans;
		moveAll(uf, step);
		int tt=step;
		for (tt=step; tt+step<t; tt+=step) {
			if (isTimeout()) break;
			ts[tt]=new TimeShift(tt, uf, set, ans);
			ts[tt].play2nd();
			uf=ts[tt].uf;
			set=ts[tt].set;
			ans=ts[tt].ans;
			moveAll(uf, step);
		}
		ts[tt]=new TimeShift(tt, uf, set, ans);
		ts[tt].play();
		long sum=0;
		for (int i=0; i+step<t; i+=step) {
			for (String s : ts[i].ls) {
				System.out.println(s);
			}
			sum+=ts[i].sum;
		}
		for (String s : ts[tt].ls) {
			System.out.println(s);
		}
		sum+=ts[tt].sum;
		long end = System.currentTimeMillis();
//		System.err.println("Elap:"+(end-start)+"ms");
//		System.err.println("Cost:"+sum);
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
	static final int time_limit = 1800;
	static boolean isTimeout() {
		long lap = System.currentTimeMillis();
		long elaps = lap - start;
        return elaps >= time_limit;
	}
	public static void main(String[] args) {
		start = System.currentTimeMillis();
		new Main().solve();
	}
	static class Dot {
		double x;
		double y;
		double vx;
		double vy;
		Dot(int x, int y, int vx, int vy) {
			this.x=x;
			this.y=y;
			this.vx=vx;
			this.vy=vy;
		}
		Dot(Dot that) {
			this.x=that.x;
			this.y=that.y;
			this.vx=that.vx;
			this.vy=that.vy;
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
