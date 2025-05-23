import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		UnionFind uf=new UnionFind(n+1);
		for (int i=0; i<m; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			uf.merge(u, v);
			uf.merge(v, u);
		}
		TreeSet<Integer> set=new TreeSet<>();
		for (int i=1; i<=n; i++) {
			int r=uf.root(i);
			set.add(r);
		}
		System.out.println(set.size());
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
/*
5 3
1 2
1 3
4 5

5 0

4 6
1 2
1 3
1 4
2 3
2 4
3 4
*/
