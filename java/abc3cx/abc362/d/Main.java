import java.util.*;
public class Main {
	static int[] ary;
	static Counter cnt;
	static long[] ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		ary=new int[n+1];
		for (int i=1; i<=n; i++) {
			ary[i]=sc.nextInt();
		}
		cnt=new Counter();
		for (int i=1; i<=m; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			int b = sc.nextInt();
			cnt.add(u, v, b);
			cnt.add(v, u, b);
		}
//		System.out.println(cnt);
		ans=new long[n+1];
		Arrays.fill(ans, Long.MAX_VALUE);
		ans[0]=0;
		ans[1]=ary[1];
		bfs(1);
//		System.out.println(Arrays.toString(ans));
		for (int i=2; i<=n; i++) {
			System.out.print(ans[i]+" ");
		}
		System.out.println();
	}
	static void bfs(int i) {
		Deque<Integer> que=new ArrayDeque<>();
		que.offer(i);
		while (que.size()>0) {
			i=que.poll();
			long x=ans[i];
//			System.out.println(i+" "+x);
			for (Pair p : cnt.get(i)) {
				int j=p.idx;
				long x2=x+p.w+ary[j];
				if (ans[j]>x2) {
					ans[j]=x2;
					que.offer(j);
				}
			}
		}
	}
	static class Pair {
		int idx;
		int w;
		Pair(int idx, int w) {
			this.idx = idx;
			this.w = w;
		}
		@Override
		public String toString() {
			return "("+idx+","+w+")";
		}
	}
	static class Counter {
		Map<Integer, List<Pair>> map = new TreeMap<>();
		int size() {
			return map.size();
		}
		List<Pair> get(int k) {
			List<Pair> v = map.get(k);
			if (v == null) v = new ArrayList<>();
			return v;
		}
		void put(int k, List<Pair> v) {
			map.put(k, v);
		}
		void add(int k, int idx, int w) {
			List<Pair> v = get(k);
			v.add(new Pair(idx, w));
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
}
/*
3 3
1 2 3
1 2 1
1 3 6
2 3 2

2 1
0 1
1 2 3

5 8
928448202 994752369 906965437 942744902 907560126
2 5 975090662
1 2 908843627
1 5 969061140
3 4 964249326
2 3 957690728
2 4 942986477
4 5 948404113
1 3 988716403
*/
