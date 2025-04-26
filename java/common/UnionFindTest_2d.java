import java.util.*;
public class Main {
	public static void main(String[] args) {
//		main0(args);
//		main02(args);
		main1(args);
	}
	public static void main0(String[] args) {
		final int N=6;
		UnionFind uf=new UnionFind(N, N);
		for (int i=1; i+2<N*N; i+=2) {
			System.out.println(uf);
			System.out.println();
			uf.merge(i, i+2);
		}
		System.out.println(uf);
		System.out.println();
	}
	public static void main02(String[] args) {
		final int N=6;
		UnionFind uf=new UnionFind(N, N);
		for (int y=1; y+2<N; y+=2) {
			for (int x=1; x+2<N; x+=2) {
				System.out.println(uf);
				System.out.println();
				uf.merge(x, y, x+2, y);
				uf.merge(x, y, x, y+2);
			}
		}
		System.out.println(uf);
		System.out.println();
	}
	//  900,000,000 (N=30,000) 1690ms 1418ms 1274ms
	//  100,000,000 (N=10,000)  183ms  163ms  151ms
	//    9,000,000 (N= 3,000)   22ms   22ms   22ms
	//    1,000,000 (N= 1,000)    5ms    6ms    9ms
	public static void main1(String[] args) {
//		final int N=10;
		final int N=1*1000;
		long st1=System.currentTimeMillis();
		UnionFind uf=new UnionFind(N, N);
		long st2=System.currentTimeMillis();
		for (int i=1; i+2<N*N; i+=2) {
			uf.merge(i, i+2);
		}
//		System.out.println(uf);
		long st3=System.currentTimeMillis();
		for (int i=1; i+2<N*N; i+=2) {
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
	static class UnionFind { //UnionFind_2d20250416
		int h;
		int w;
		int[] uf;
		public UnionFind(int h, int w) {
			this.h = h;
			this.w = w;
			int n = h*w;
			this.uf = new int[n];
			for (int i=0; i<n; i++) uf[i] = -1;
		}
		public int root(int x1, int y1) {
			int u=x1+y1*w;
			return root(u);
		}
		public int root(int v) {
			if (uf[v] < 0) return v;
			uf[v] = root(uf[v]);
			return uf[v];
		}
		public void merge(int x1, int y1, int x2, int y2) {
			int u=x1+y1*w;
			int v=x2+y2*w;
			merge(u, v);
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
		public boolean same(int x1, int y1, int x2, int y2) {
			int u=x1+y1*w;
			int v=x2+y2*w;
			return same(u, v);
		}
		public boolean same(int u, int v) {
			return root(u) == root(v);
		}
		public int size(int x1, int y1) {
			int u=x1+y1*w;
			return size(u);
		}
		public int size(int v) {
			v = root(v);
			return -uf[v];
		}
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (int y=0; y<h; y++) {
				List<Integer> list = new ArrayList<>();
				for (int x=0; x<w; x++) {
					list.add(uf[y*w+x]);
				}
				if (sb.length() > 0) sb.append(System.getProperty("line.separator"));
				sb.append(list);
			}
			return sb.toString();
		}
	}
}
/*
*/
