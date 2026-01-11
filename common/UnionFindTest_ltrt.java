import java.util.*;
public class Main {
	public static void main(String[] args) {
//		main0(args);
		main1(args);
	}
	public static void main0(String[] args) {
		final int N=10;
		UnionFind uf=new UnionFind(N);
		for (int i=1; i+2<N; i+=2) {
			System.out.println(uf);
			uf.merge(i, i+2);
		}
		System.out.println(uf);
	}
	//1,000,000,000 ----ms ----ms ----ms
	//  100,000,000  590ms  289ms  147ms
	//   10,000,000   58ms   37ms   24ms
	//    1,000,000    8ms    8ms    9ms
	public static void main1(String[] args) {
//		final int N=1000;
		final int N=1*1000*1000;
		long st1=System.currentTimeMillis();
		UnionFind uf=new UnionFind(N);
		long st2=System.currentTimeMillis();
		for (int i=1; i+2<N; i+=2) {
			uf.merge(i, i+2);
		}
//		System.out.println(uf);
		long st3=System.currentTimeMillis();
		for (int i=1; i+2<N; i+=2) {
			if (!uf.same(1, i)) {
				System.out.println(i);
			}
			if (uf.same(1, i-1)) {
				System.out.println(i);
			}
		}
		long st4=System.currentTimeMillis();
		long tm2=st2-st1;
		long tm3=st3-st2;
		long tm4=st4-st3;
		System.out.println("end of main1 "+tm2+" "+tm3+" "+tm4);
	}
	static class UnionFind { //UnionFind_ltrt20250416
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
*/
