import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		UnionFind uf=new UnionFind(n+1);
		TreeSet<Integer> set=new TreeSet<>();
		for (int i=0; i<m; i++) {
			int a=sc.nextInt();
			String b=sc.next();
			int c=sc.nextInt();
			String d=sc.next();
			if (uf.same(a, c)) {
				int r=uf.root(a);
				set.add(r);
			}
			uf.merge(a, c);
		}
		TreeSet<Integer> set2=new TreeSet<>();
		for (int i=1; i<=n; i++) {
			int r=uf.root(i);
			if (set.contains(r)) continue;
			if (set2.contains(r)) continue;
			set2.add(r);
		}
		System.out.println(set.size()+" "+set2.size());
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
3 R 5 B
5 R 3 B
4 R 2 B

7 0

7 6
5 R 3 R
7 R 4 R
4 B 1 R
2 R 3 B
2 B 5 B
1 B 7 B
*/
