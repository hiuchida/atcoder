import java.util.*;
public class Main {
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
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int q = sc.nextInt();
		UnionFind uf = new UnionFind(n);
		for (int i=0; i<q; i++) {
			int p = sc.nextInt();
			int a = sc.nextInt();
			int b = sc.nextInt();
			if (p == 0) {
				uf.merge(a, b);
//				System.out.println(uf);
//				System.out.println(uf.size(a) + " " + uf.size(b));
			} else {
//				System.out.println(uf.size(a) + " " + uf.size(b));
				if (uf.same(a, b)) {
					System.out.println("Yes");
				} else {
					System.out.println("No");
				}
			}
		}
	}
}
