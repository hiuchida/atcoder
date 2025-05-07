import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		int[][] ary=new int[m][2];
		for (int i=0; i<m; i++) {
			int a=sc.nextInt();
			int b=sc.nextInt();
			ary[i][0]=a;
			ary[i][1]=b;
		}
//		for (int i=0; i<m; i++) {
//			System.out.println(Arrays.toString(ary[i]));
//		}
		int ans=0;
		for (int i=0; i<m; i++) {
			UnionFind uf=new UnionFind(n+1);
			for (int j=0; j<m; j++) {
				if (i==j) continue;
				uf.merge(ary[j][0], ary[j][1]);
			}
			int cnt=0;
			for (int j=1; j<=n; j++) {
				if (j==uf.root(j)) cnt++;
			}
//			System.out.println(i+" "+cnt);
			if (cnt>1) ans++;
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
7 7
1 3
2 7
3 4
4 5
4 6
5 6
6 7

3 3
1 2
1 3
2 3

6 5
1 2
2 3
3 4
4 5
5 6
*/
