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
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] aa = new int[m];
		int[] ab = new int[m];
		UnionFind uf = new UnionFind(n+1);
		for (int i=0; i<m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			aa[i] = a;
			ab[i] = b;
			uf.merge(a, b);
		}
		DEBUG(aa);
		DEBUG(ab);
		DEBUG(uf);
		TreeMap<Integer, Integer> map = new TreeMap<>();
		long ans = 0;
		for (int i=1; i<=n; i++) {
			int v = uf.root(i);
			if (!map.containsKey(v)) {
				int s = uf.size(v);
				map.put(v, s);
				ans = Math.max(ans, s);
			}
		}
		DEBUG(map);
		System.out.println(ans);
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
