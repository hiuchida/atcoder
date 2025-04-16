import java.util.*;
public class Main {
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
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] aa = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			aa[i] = sc.nextInt();
		}
		int[] ab = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			ab[i] = sc.nextInt();
		}
		UnionFind uf = new UnionFind(n+1);
		for (int i = 1; i <= m; i++) {
			int c = sc.nextInt();
			int d = sc.nextInt();
			uf.merge(c, d);
		}
//		DEBUG(aa);
//		DEBUG(ab);
//		DEBUG(uf);
		TreeMap<Integer, Long> map = new TreeMap<>();
		for (int i=1; i<=n; i++) {
			int v = uf.root(i);
			Long ll = map.get(v);
			if (ll == null) ll=0L;
			long dif = ab[i]-aa[i];
			ll+=dif;
			map.put(v, ll);
		}
//		DEBUG(map);
		for (int i : map.keySet()) {
			if (map.get(i) != 0) ng();
		}
		ok();
	}

	static void ok() {
		System.out.println("Yes");
		System.exit(0);
	}
	static void ng() {
		System.out.println("No");
		System.exit(0);
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
