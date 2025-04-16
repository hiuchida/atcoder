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
		Arrays.sort(ary);
//		System.out.println(Arrays.toString(ary));
		UnionFind uf=new UnionFind(m);
		for (int i=1; i<n; i++) {
			if (ary[i-1]+1==ary[i]) {
				uf.merge(ary[i-1], ary[i]);
			}
		}
		if (ary[0]==0 && ary[n-1]==m-1) {
			uf.merge(ary[0], ary[n-1]);
		}
//		System.out.println(uf);
		Counter cnt=new Counter();
		for (int i=0; i<n; i++) {
			int r=uf.root(ary[i]);
			cnt.add(r, ary[i]);
		}
//		System.out.println(cnt);
		List<Long> list=new ArrayList<>();
		for (int k : cnt.keySet()) {
			Bean v=cnt.get(k);
			list.add(v.v);
		}
		Collections.sort(list);
//		System.out.println(list);
		list.remove(list.size()-1);
		long ans=0;
		for (long v : list) {
			ans+=v;
		}
		System.out.println(ans);
	}
	static class Bean { //Counter_int_bean20250416
		long v;
		Bean(long v) {
			this.v = v;
		}
		@Override
		public String toString() {
			return "("+v+")";
		}
	}
	static class Counter { //Counter_int_bean20250416
		Map<Integer, Bean> map = new TreeMap<>();
		int size() {
			return map.size();
		}
		Bean get(int k) {
			Bean v = map.get(k);
			if (v == null) v = new Bean(0);
			return v;
		}
		void put(int k, Bean v) {
			map.put(k, v);
		}
		void remove(int k) {
			map.remove(k);
		}
		void add(int k, int x) {
			Bean v = get(k);
			v.v += x;
			put(k, v);
		}
		NavigableSet<Integer> keySet() {
			return (NavigableSet<Integer>) map.keySet();
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
9 7
3 0 2 5 5 3 0 6 3

1 10
4

20 20
18 16 15 9 8 8 17 1 3 17 11 9 12 11 7 3 2 14 3 12
*/
