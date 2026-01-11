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
	//1,000,000,000 1818ms 1543ms 1382ms
	//  100,000,000  208ms  163ms  152ms
	//   10,000,000   22ms   22ms   24ms
	//    1,000,000    4ms    6ms    9ms
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
*/
