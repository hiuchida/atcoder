import java.util.*;
public class Main {
	static int h;
	static int w;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		h = sc.nextInt();
		w = sc.nextInt();
		boolean[][] map=new boolean[h][w];
		UnionFind uf=new UnionFind(h, w);
		for (int y=0; y<h; y++) {
			String s = sc.next();
			for (int x=0; x<w; x++) {
				if (s.charAt(x)=='#') map[y][x]=true;
			}
		}
		for (int y=0; y<h; y++) {
			for (int x=0; x<w; x++) {
				if (check(map, y, x)) {
					if (y-1>=0) uf.merge(addr(y, x), addr(y-1, x));
					if (y+1<h) uf.merge(addr(y, x), addr(y+1, x));
					if (x-1>=0) uf.merge(addr(y, x), addr(y, x-1));
					if (x+1<w) uf.merge(addr(y, x), addr(y, x+1));
				}
				System.out.println(uf);
				System.out.println();
			}
		}
		for (int y=0; y<h; y++) {
			System.out.println(Arrays.toString(map[y]));
		}
		System.out.println(uf);
	}
	static int addr(int y, int x) {
		return y*w+x;
	}
	static boolean check(boolean[][] map, int y, int x) {
		if (map[y][x]) return false;
		if (y-1>=0 && map[y-1][x]) return false;
		if (y+1<h && map[y+1][x]) return false;
		if (x-1>=0 && map[y][x-1]) return false;
		if (x+1<w && map[y][x+1]) return false;
		return true;
	}
	static class UnionFind { //UnionFind_2d20250416
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
		public int root(int x1, int y1) {
			int u=x1+y1*w;
			return root(u);
		}
		public int root(int v) {
			if (uf[v] < 0) return v;
			uf[v] = root(uf[v]);
			return uf[v];
		}
		public void merge(int x1, int y1, int x2, int y2) {
			int u=x1+y1*w;
			int v=x2+y2*w;
			merge(u, v);
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
		public boolean same(int x1, int y1, int x2, int y2) {
			int u=x1+y1*w;
			int v=x2+y2*w;
			return same(u, v);
		}
		public boolean same(int u, int v) {
			return root(u) == root(v);
		}
		public int size(int x1, int y1) {
			int u=x1+y1*w;
			return size(u);
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
					list.add(uf[y*w+x]);
				}
				if (sb.length() > 0) sb.append(System.getProperty("line.separator"));
				sb.append(list);
			}
			return sb.toString();
		}
	}
}
/*
3 5
.#...
.....
.#..#

3 3
..#
#..
..#
*/
