import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		int[] ary=new int[n+1];
		UnionFind uf=new UnionFind(n+1);
		for (int i=0; i<m; i++) {
			int a=sc.nextInt();
			int b=sc.nextInt();
			ary[a]++;
			ary[b]++;
			uf.merge(a, b);
		}
		for (int i=1; i<=n; i++) {
			if (ary[i]!=2) {
				System.out.println("No");
				System.exit(0);
			}
		}
		if (uf.size(1)==n) System.out.println("Yes");
		else System.out.println("No");
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
4 4
2 4
3 1
4 1
2 3

4 6
1 2
1 3
1 4
2 3
2 4
3 4
*/
/*
6 6
1 2
2 3
3 1
4 5
5 6
6 4
*/
