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
			cnt.add(u, new Bean(v, b));
			cnt.add(v, new Bean(u, b));
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
			for (Bean obj : cnt.get(i)) {
				int j=obj.v;
				long x2=x+obj.w+ary[j];
				if (ans[j]>x2) {
					ans[j]=x2;
					que.offer(j);
				}
			}
		}
	}
	static class Bean { //Counter_int_listbean20250414
		int v;
		int w;
		Bean(int v, int w) {
			this.v = v;
			this.w = w;
		}
		@Override
		public String toString() {
			return "("+v+","+w+")";
		}
	}
	static class Counter { //Counter_int_listbean20250414
		Map<Integer, List<Bean>> map = new TreeMap<>();
		int size() {
			return map.size();
		}
		List<Bean> get(int k) {
			List<Bean> v = map.get(k);
			if (v == null) v = new ArrayList<>();
			return v;
		}
		void put(int k, List<Bean> v) {
			map.put(k, v);
		}
		void remove(int k) {
			map.remove(k);
		}
		void add(int k, Bean val) {
			List<Bean> v = get(k);
			v.add(val);
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
