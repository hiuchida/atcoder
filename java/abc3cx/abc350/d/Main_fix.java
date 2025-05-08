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
			long all=calc(s, 2);
			ans+=(all-val/2);
		}
		System.out.println(ans);
	}
	static long calc(int a, int b) { //abc295_d,abc350_d,abc355_d,typical90_084: aCbの組み合わせ数
		long ans=1;
		for (int i=0; i<b; i++) ans*=a-i;
		for (int i=1; i<=b; i++) ans/=i;
		return ans;
	}
	static class Counter { //Counter_int_int20250410
		Map<Integer, Integer> map = new TreeMap<>();
		int size() {
			return map.size();
		}
		int get(int k) {
			Integer v = map.get(k);
			if (v == null) v = 0;
			return v;
		}
		void put(int k, int v) {
			if (v==0) map.remove(k);
			else map.put(k, v);
		}
		void inc(int k) {
			int v = get(k);
			v++;
			put(k, v);
		}
		void dec(int k) {
			int v = get(k);
			v--;
			put(k, v);
		}
		void add(int k, int x) {
			int v = get(k);
			v += x;
			put(k, v);
		}
		void sub(int k, int x) {
			int v = get(k);
			v -= x;
			put(k, v);
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
