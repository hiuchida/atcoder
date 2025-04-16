import java.util.*;
public class Main {
	static int h;
	static int w;
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
			if (uw <= vw) {
				uf[u] += uf[v];
				uf[v] = u;
			} else if (uw > vw) {
				uf[v] += uf[u];
				uf[u] = v;
			}
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
	static TreeSet<Point> set = new TreeSet<>();
	static TreeMap<Integer, UnionFind> mapx = new TreeMap<>();
	static TreeMap<Integer, UnionFind> mapy = new TreeMap<>();
	static UnionFind getx(int x) {
		UnionFind uf = mapx.get(x);
		if (uf == null) {
			uf = new UnionFind(h+2);
			mapx.put(x, uf);
		}
		return uf;
	}
	static UnionFind gety(int y) {
		UnionFind uf = mapy.get(y);
		if (uf == null) {
			uf = new UnionFind(w+2);
			mapy.put(y, uf);
		}
		return uf;
	}
	static void add(Point p) {
		set.add(p);
		UnionFind ufy = getx(p.x);
		Point p2;
		p2 = new Point(p.y-1, p.x);
		if (set.contains(p2)) ufy.merge(p.y, p2.y);
		p2 = new Point(p.y+1, p.x);
		if (set.contains(p2)) ufy.merge(p.y, p2.y);
		UnionFind ufx = gety(p.y);
		p2 = new Point(p.y, p.x-1);
		if (set.contains(p2)) ufx.merge(p.x, p2.x);
		p2 = new Point(p.y, p.x+1);
		if (set.contains(p2)) ufx.merge(p.x, p2.x);
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		h = sc.nextInt();
		w = sc.nextInt();
		int q = sc.nextInt();
		for (int i=0; i<q; i++) {
//			System.out.println("s=" + set);
//			System.out.println("x=" + mapx);
//			System.out.println("y=" + mapy);
			int r = sc.nextInt();
			int c = sc.nextInt();
			Point p = new Point(r, c);
			if (!set.contains(p)) {
				add(p);
			} else {
				UnionFind ufy = getx(p.x);
				for (int tp=p.y-1; tp>0; tp--) {
					if (!ufy.same(p.y, tp)) {
						ufy.merge(p.y, tp);
						Point p2 = new Point(tp, p.x);
						add(p2);
						break;
					}
				}
				for (int bm=p.y+1; bm<=h; bm++) {
					if (!ufy.same(p.y, bm)) {
						ufy.merge(p.y, bm);
						Point p2 = new Point(bm, p.x);
						add(p2);
						break;
					}
				}
				UnionFind ufx = gety(p.y);
				for (int lt=p.x-1; lt>0; lt--) {
					if (!ufx.same(lt, p.x)) {
						ufx.merge(lt, p.x);
						Point p2 = new Point(p.y, lt);
						add(p2);
						break;
					}
				}
				for (int rt=p.x+1; rt<=w; rt++) {
					if (!ufx.same(rt, p.x)) {
						ufx.merge(rt, p.x);
						Point p2 = new Point(p.y, rt);
						add(p2);
						break;
					}
				}
			}
		}
//		System.out.println("s=" + set);
//		System.out.println("x=" + mapx);
//		System.out.println("y=" + mapy);
		long ans = h*w;
		ans -= set.size();
		System.out.println(ans);
	}
	static class Point implements Comparable<Point> {
		final int y;
		final int x;
		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
		@Override
		public int compareTo(Main.Point that) {
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
			return "(" + y + ", " + x + ")";
		}
	}
}
