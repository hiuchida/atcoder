import java.util.*;
public class Main {
	static int N=1 << 20;
	static long[] ary=new long[N];
	static UnionFind uf=new UnionFind(N);
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Arrays.fill(ary, -1);
		int q = sc.nextInt();
		for (int i=0; i<q; i++) {
			int t = sc.nextInt();
			long x = sc.nextLong();
			int h=(int)(x%N);
			if (t==1) {
				if (ary[h]<0) {
					set(h, x);
				} else {
					int rt=uf.right(h);
					rt=(rt+1)%N;
					if (rt==0) {
						if (ary[rt]<0) {
							set(rt, x);
						} else {
							int rt2=uf.right(rt);
							rt2=(rt2+1)%N;
							set(rt2, x);
						}
					} else {
						set(rt, x);
					}
				}
//				while (ary[h]>=0) h=(h+1)%N;
//				ary[h]=x;
			} else {
				System.out.println(ary[h]);
			}
		}
	}
	static void set(int h, long x) {
		if (ary[h]>=0) throw new RuntimeException();
		ary[h]=x;
		if (h>0 && ary[h-1]>=0) {
			uf.merge(h, h-1);
		}
		if (h<N-1 && ary[h+1]>=0) {
			uf.merge(h, h+1);
		}
	}
	static class UnionFind {
		int[] uf;
		int[] lt;
		int[] rt;
		public UnionFind(int n) {
			uf = new int[n];
			lt = new int[n];
			rt = new int[n];
			for (int i=0; i<n; i++) {
				uf[i] = -1;
				lt[i] = i;
				rt[i] = i;
			}
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
				lt[u] = Math.min(lt[u], lt[v]);
				rt[u] = Math.max(rt[u], rt[v]);
				uf[v] = u;
				lt[v] = -1;
				rt[v] = -1;
			} else if (uw > vw) {
				uf[v] += uf[u];
				lt[v] = Math.min(lt[u], lt[v]);
				rt[v] = Math.max(rt[u], rt[v]);
				uf[u] = v;
				lt[u] = -1;
				rt[u] = -1;
			}
		}
		public boolean same(int u, int v) {
			return root(u) == root(v);
		}
		public int size(int v) {
			v = root(v);
			return -uf[v];
		}
		public int left(int v) {
			v = root(v);
			return lt[v];
		}
		public int right(int v) {
			v = root(v);
			return rt[v];
		}
		@Override
		public String toString() {
			List<String> list = new ArrayList<>();
			for (int i=0; i<uf.length; i++) {
				if (uf[i] < -1) {
					list.add(-uf[i] + "(" + lt[i] + "-" + rt[i] + ")");
				} else if (uf[i] < 0) {
					list.add(""+ (-uf[i]));
				} else {
					list.add("*"+uf[i]);
				}
			}
			return "[" + String.join(", ", list) + "]";
		}
	}
}
/*
4
1 1048577
1 1
2 2097153
2 3
*/
/*
8
1 1048577
1 1048576
1 1048575
1 1048575
2 1048575
2 0
2 1
2 2
*/
