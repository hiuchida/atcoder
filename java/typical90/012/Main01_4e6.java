import java.util.*;
public class Main {
	static final int[] DY = { -1,1, 0,0 }; //UDLR
	static final int[] DX = {  0,0,-1,1 }; //UDLR
	static int h; //height=row=y
	static int w; //width=column=x
	static TreeSet<Point> set = new TreeSet<>();
	static UnionFind uf;
	static boolean isAvail(int y, int x) {
		if (0 < y && y <= h && 0 < x && x <= w) return true;
		return false;
	}
	static boolean isAvail(Point p) {
		return isAvail(p.y, p.x);
	}
	static int addr(int y, int x) {
		return (y-1)*h+(x-1);
	}
	static int addr(Point p) {
		return addr(p.y, p.x);
	}
	static void add(Point p) {
		if (!set.contains(p)) {
			set.add(p);
		}
		for (int d=0; d<DY.length; d++) {
			Point p2 = new Point(p.y+DY[d], p.x+DX[d]);
			if (isAvail(p2)) {
				if (set.contains(p2)) {
					uf.merge(addr(p), addr(p2));
				}
			}
		}
	}
	static boolean same(Point p1, Point p2) {
		if (!set.contains(p1) || !set.contains(p2)) return false;
		return uf.same(addr(p1), addr(p2));
	}
	static void DEBUG(Object x) {
		System.out.println(x);
	}
	static void DEBUG(long x) {
		DEBUG(""+x);
    }
	static void DEBUG(int[] x) {
		DEBUG(Arrays.toString(x));
    }
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		h = sc.nextInt();
		w = sc.nextInt();
		uf = new UnionFind(h*w, h);
		int q = sc.nextInt();
		for (int i=0; i<q; i++) {
			int z = sc.nextInt();
			int a, b, c, d;
			a = sc.nextInt();
			b = sc.nextInt();
			Point p = new Point(a, b);
			switch (z) {
			case 1:
				add(p);
				break;
			case 2:
				c = sc.nextInt();
				d = sc.nextInt();
				Point p2 = new Point(c, d);
				if (same(p, p2)) ok();
				else ng();
				break;
			}
		}
//		DEBUG(set);
//		DEBUG(uf);
	}
	static void ok() {
		System.out.println("Yes");
	}
	static void ng() {
		System.out.println("No");
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
			return "(" + y + "," + x + ")";
		}
	}
	static class UnionFind {
		int h;
		int[] uf;
		public UnionFind(int n, int h) {
			this.h = h;
			this.uf = new int[n];
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
			StringBuilder sb = new StringBuilder();
			for (int y=0; y<h; y++) {
				List<Integer> list = new ArrayList<>();
				for (int x=0; x<uf.length/h; x++) {
					list.add(uf[y*h+x]);
				}
				if (sb.length() > 0) sb.append(System.getProperty("line.separator"));
				sb.append(list);
			}
			return sb.toString();
		}
	}
}
