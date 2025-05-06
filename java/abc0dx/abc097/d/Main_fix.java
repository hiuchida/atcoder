import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		UnionFind uf=new UnionFind(n+1);
		for (int i=0; i<m; i++) {
			int x=sc.nextInt();
			int y=sc.nextInt();
			uf.merge(x, y);
		}
		int ans=0;
		for (int i=0; i<n; i++) {
			int p=ary[i];
			if (uf.same(i+1, p)) ans++;
		}
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
/*
5 2
5 3 1 4 2
1 3
5 4

3 2
3 2 1
1 2
2 3

10 8
5 3 6 8 7 10 9 1 2 4
3 1
4 1
5 9
2 5
6 5
3 5
8 9
7 9

5 1
1 2 3 4 5
1 5
*/
