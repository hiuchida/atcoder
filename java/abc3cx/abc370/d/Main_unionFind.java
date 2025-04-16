import java.util.*;
public class Main {
	static int h;
	static int w;
	static class UnionFind { //unused UnionFind_ltrt
		int[] uf;
		int[] lt;
		int[] rt;
		public UnionFind(int n) {
			uf = new int[n];
			lt = new int[n];
			rt = new int[n];
			for (int i=0; i<n; i++) {
				uf[i] = -1;
				lt[i] = i;
				rt[i] = i;
			}
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
				lt[u] = Math.min(lt[u], lt[v]);
				rt[u] = Math.max(rt[u], rt[v]);
				uf[v] = u;
				lt[v] = -1;
				rt[v] = -1;
			} else if (uw > vw) {
				uf[v] += uf[u];
				lt[v] = Math.min(lt[u], lt[v]);
				rt[v] = Math.max(rt[u], rt[v]);
				uf[u] = v;
				lt[u] = -1;
				rt[u] = -1;
			}
		}
		public boolean same(int u, int v) {
			return root(u) == root(v);
		}
		public int size(int v) {
			v = root(v);
			return -uf[v];
		}
		public int left(int v) {
			v = root(v);
			return lt[v];
		}
		public int right(int v) {
			v = root(v);
			return rt[v];
		}
		@Override
		public String toString() {
			return Arrays.toString(uf)
					+ Arrays.toString(lt)
					+ Arrays.toString(rt);
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
//			System.out.println("p=" + p);
			if (!set.contains(p)) {
				add(p);
			} else {
				UnionFind ufy = getx(p.x);
				int tp=ufy.left(p.y) - 1;
				if (tp>0) {
					ufy.merge(p.y, tp);
					Point p2 = new Point(tp, p.x);
					add(p2);
				}
				int bm=ufy.right(p.y) + 1;
				if (bm<=h) {
					ufy.merge(p.y, bm);
					Point p2 = new Point(bm, p.x);
					add(p2);
				}
				UnionFind ufx = gety(p.y);
				int lt=ufx.left(p.x) - 1;
				if (lt>0) {
					ufx.merge(lt, p.x);
					Point p2 = new Point(p.y, lt);
					add(p2);
				}
				int rt=ufx.right(p.x) + 1;
				if (rt<=w) {
					ufx.merge(rt, p.x);
					Point p2 = new Point(p.y, rt);
					add(p2);
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
