import java.util.*;
public class Main {
	public static void main(String[] args) {
//		main0(100);
		main1(100);
	}
	public static void main0(int k) {
		final int N=10;
		UnionFind uf=new UnionFind();
		for (int i=1; i+2<N; i+=2) {
			System.out.println(uf);
			uf.merge(k*i, k*(i+2));
		}
		System.out.println(uf);
	}
	//1,000,000,000 ----ms ----ms ----ms
	//  100,000,000 ----ms ----ms ----ms
	//   10,000,000    1ms 3140ms 2116ms
	//    1,000,000    1ms  271ms  195ms
	public static void main1(int k) {
//		final int N=1000;
		final int N=1*1000*1000;
		long st1=System.currentTimeMillis();
		UnionFind uf=new UnionFind();
		long st2=System.currentTimeMillis();
		for (int i=1; i+2<N; i+=2) {
			uf.merge(k*i, k*(i+2));
		}
//		System.out.println(uf);
		long st3=System.currentTimeMillis();
		for (int i=1; i+2<N; i+=2) {
			if (!uf.same(k*1, k*i)) {
				System.out.println(i);
			}
			if (uf.same(k*1, k*i-1)) {
				System.out.println(i);
			}
		}
		long st4=System.currentTimeMillis();
		long tm2=st2-st1;
		long tm3=st3-st2;
		long tm4=st4-st3;
		System.out.println("end of main1 "+tm2+" "+tm3+" "+tm4);
	}
	static class UnionFind { //UnionFind_map20250416
		Map<Integer, Integer> map=new TreeMap<>();
		public UnionFind() {
		}
		public int root(int v) {
			Integer vv = map.get(v);
			if (vv == null) {
				vv = -1;
				map.put(v, vv);
			}
			if (vv < 0) return v;
			vv = root(vv);
			map.put(v, vv);
			return vv;
		}
		public void merge(int u, int v) {
			u = root(u);
			v = root(v);
			if (u == v) return;
			int uw = map.get(u);
			int vw = map.get(v);
			int w = uw + vw;
			if (uw <= vw) {
				map.put(u, w);
				map.put(v, u);
			} else {
				map.put(v, w);
				map.put(u, v);
			}
		}
		public boolean same(int u, int v) {
			return root(u) == root(v);
		}
		public int size(int v) {
			v = root(v);
			return -map.get(v);
		}
		@Override
		public String toString() {
			return map.toString();
		}
	}
}
/*
*/
