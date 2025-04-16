import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		Counter cnt=new Counter();
		UnionFind uf=new UnionFind(n+1);
		for (int i=0; i<m; i++) {
			int u=sc.nextInt();
			int v=sc.nextInt();
			cnt.add(u, v);
			cnt.add(v, u);
			uf.merge(u, v);
		}
//		System.out.println(cnt);
//		System.out.println(uf);
		int[] ary=new int[n+1];
		Arrays.fill(ary, -1);
		for (int i=1; i<=n; i++) {
			if (ary[i]<0) {
				Deque<Que> que=new ArrayDeque<>();
				que.offer(new Que(i, 0));
				ary[i]=0;
				while (que.size()>0) {
					Que b=que.poll();
					int nxtidx=(b.pre+1);
//					int nxtidx=(b.pre+1)%2;
					for (int nxt : cnt.get(b.cur)) {
						if (ary[nxt]<0) {
							que.offer(new Que(nxt, nxtidx));
							ary[nxt]=nxtidx;
						}
					}
				}
			}
		}
//		System.out.println(Arrays.toString(ary));
		System.out.println();
	}
	static class Que { //Que_curpre20250416
		int cur;
		int pre;
		Que(int cur, int pre) {
			this.cur=cur;
			this.pre=pre;
		}
		@Override
		public String toString() {
			return "(" + pre + "->" + cur + ")";
		}
	}
	static class Counter { //Counter_int_listint20250410
		Map<Integer, List<Integer>> map = new TreeMap<>();
		int size() {
			return map.size();
		}
		List<Integer> get(int k) {
			List<Integer> v = map.get(k);
			if (v == null) v = new ArrayList<>();
			return v;
		}
		void put(int k, List<Integer> v) {
			map.put(k, v);
		}
		void remove(int k) {
			map.remove(k);
		}
		void add(int k, int idx) {
			List<Integer> v = get(k);
			v.add(idx);
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
5 4
4 2
3 1
5 2
3 2

4 3
3 1
3 2
1 2

9 11
4 9
9 1
8 2
8 3
9 2
8 4
6 7
4 6
7 5
4 5
7 8
*/
