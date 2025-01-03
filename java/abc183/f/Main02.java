import java.util.*;
public class Main {
	static void DEBUG(Object x) {
//		System.out.println(x);
	}
	static void DEBUG(long x) {
		DEBUG(""+x);
    }
	static void DEBUG(int[] x) {
		DEBUG(Arrays.toString(x));
    }
	static TreeMap<Integer, Counter> map = new TreeMap<>();
	static int[] ary;
	static UnionFind uf;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int q = sc.nextInt();
		ary = new int[n+1];
		uf = new UnionFind(n+1);
		for (int i=1; i<=n; i++) {
			int c = sc.nextInt();
			ary[i] = c;
		}
		DEBUG(ary);
		DEBUG(uf);
		for (int i=0; i<q; i++) {
			int c = sc.nextInt();
			int a = sc.nextInt();
			int b = sc.nextInt();
			int pa;
			Counter c1;
			switch (c) {
			case 1:
				pa = uf.root(a);
				int pb = uf.root(b);
				if (pa == pb) continue;
				c1 = map.get(pa);
				Counter c2 = map.get(pb);
				Counter c3 = add(a, b, c1, c2);
				uf.merge(a, b);
				int pc = uf.root(a);
				map.put(pc, c3);
				if (pa == pc) map.remove(pb);
				else map.remove(pa);
				DEBUG(a + " " + b + " " + c3 + " " + map);
				DEBUG(uf);
				break;
			case 2:
				pa = uf.root(a);
				c1 = map.get(pa);
				if (c1 == null) {
					c1 = new Counter();
					c1.inc(ary[a]);
				}
				int v = c1.get(b);
				System.out.println(v);
				break;
			}
		}
	}
	static Counter add(int a, int b, Counter c1, Counter c2) {
		if (c1 == null) {
			c1 = new Counter();
			c1.inc(ary[a]);
		}
		if (c2 == null) {
			c2 = new Counter();
			c2.inc(ary[b]);
		}
		if (c1.size() <= c2.size()) {
			for (int i : c1.keySet()) {
				c2.add(i, c1.get(i));
			}
			return c2;
		}
		for (int i : c2.keySet()) {
			c1.add(i, c2.get(i));
		}
		return c1;
	}

	static class Counter {
		Map<Integer, Integer> map = new TreeMap<>();
		int size() {
			return map.size();
		}
		int get(int c) {
			Integer v = map.get(c);
			if (v == null) v = 0;
			return v;
		}
		void inc(int c) {
			int v = get(c);
			v++;
			map.put(c, v);
		}
		void add(int c, int x) {
			int v = get(c);
			v += x;
			map.put(c, v);
		}
		Set<Integer> keySet() {
			return map.keySet();
		}
		@Override
		public String toString() {
			return map.toString();
		}
	}
	static class UnionFind {
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
}
