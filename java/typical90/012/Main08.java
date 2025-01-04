import java.util.*;
public class Main {
	static final int[] DY = { -1, 1, 0, 0 }; //UDLR
	static final int[] DX = {  0, 0,-1, 1 }; //UDLR
	static int h; //height=row=y
	static int w; //width=column=x
	static boolean[][] map;
	static UnionFind uf;
	static int addr(int y, int x) {
		return (y-0)*(h+0)+(x-0);
	}
	static void add(int py, int px) {
		map[py][px] = true; 
		for (int d=0; d<DY.length; d++) {
			int qy=py+DY[d];
			int qx=px+DX[d];
			if (map[qy][qx]) {
				uf.merge(addr(py, px), addr(qy, qx));
			}
		}
	}
	static boolean same(int py, int px, int qy, int qx) {
		if (!map[py][px]) return false;
		if (!map[qy][qx]) return false;
		return uf.same(addr(py, px), addr(qy, qx));
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
		map = new boolean[h+2][w+2];
		uf = new UnionFind(h+2, w+2);
		int q = sc.nextInt();
		for (int i=0; i<q; i++) {
			int z = sc.nextInt();
			int a, b, c, d;
			a = sc.nextInt();
			b = sc.nextInt();
			switch (z) {
			case 1:
				add(a, b);
				break;
			case 2:
				c = sc.nextInt();
				d = sc.nextInt();
				if (same(a, b, c, d)) ok();
				else ng();
				break;
			}
		}
//		for (int y=0; y<h+2; y++) {
//			DEBUG(Arrays.toString(map[y]));
//		}
//		DEBUG(set);
//		DEBUG(uf);
	}
	static void ok() {
		System.out.println("Yes");
	}
	static void ng() {
		System.out.println("No");
	}

	static class UnionFind {
		int h;
		int w;
		int[] uf;
		public UnionFind(int h, int w) {
			this.h = h;
			this.w = w;
			int n = h*w;
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
				for (int x=0; x<w; x++) {
					list.add(uf[y*h+x]);
				}
				if (sb.length() > 0) sb.append(System.getProperty("line.separator"));
				sb.append(list);
			}
			return sb.toString();
		}
	}
}
