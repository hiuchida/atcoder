import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		Counter c=new Counter();
		UnionFind uf=new UnionFind(n+1);
		for (int i=0; i<m; i++) {
			int a=sc.nextInt();
			int b=sc.nextInt();
			c.inc(a);
			c.inc(b);
			uf.merge(a, b);
		}
//		System.out.println(c);
//		System.out.println(uf);
		TreeMap<Integer, Integer> map=new TreeMap<>();
		for (int i=1; i<=n; i++) {
			int r=uf.root(i);
			Integer val=map.get(r);
			if (val==null) {
				val=0;
			}
			val+=c.get(i);
			map.put(r, val);
		}
//		System.out.println(map);
		long ans=0;
		for (int i : map.keySet()) {
			int val=map.get(i);
			int r=uf.root(i);
			int s=uf.size(r);
			int all=s*(s-1)/2; //abc295_d,abc350_d,abc355_d,typical90_084: aCbの組み合わせ数
			ans+=(all-val/2);
		}
		System.out.println(ans);
	}
	static class Counter {
		Map<Integer, Integer> map = new TreeMap<>();
		int size() {
			return map.size();
		}
		int get(int c) {
			Integer v = map.get(c);
			if (v == null) v = 0;
			return v;
		}
		void inc(int c) {
			int v = get(c);
			v++;
			map.put(c, v);
		}
		void dec(int c) {
			int v = get(c);
			v--;
			if (v==0) map.remove(c);
			else map.put(c, v);
		}
		void add(int c, int x) {
			int v = get(c);
			v += x;
			map.put(c, v);
		}
		void sub(int c, int x) {
			int v = get(c);
			v -= x;
			if (v==0) map.remove(c);
			else map.put(c, v);
		}
		Set<Integer> keySet() {
			return map.keySet();
		}
		@Override
		public String toString() {
			return map.toString();
		}
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
