import java.util.*;
public class Main {
	static final long M=1000000007; //10^9+7
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		Counter cnt=new Counter();
		for (int i=0; i<m; i++) {
			int u=sc.nextInt();
			int v=sc.nextInt();
			cnt.add(u, v);
			cnt.add(v, u);
		}
//		System.out.println(cnt);
		int[] cost=new int[n+1];
		Arrays.fill(cost, -1);
		cost[1]=0;
		long[] ans=new long[n+1];
		ans[1]=1;
		Deque<Integer> que=new ArrayDeque<>();
		que.offer(1);
		while (que.size()>0) {
			int cur=que.poll();
//			System.out.println(cur);
			for (int nxt : cnt.get(cur)) {
				if (cost[nxt]<0) {
					cost[nxt]=cost[cur]+1;
					ans[nxt]=ans[cur];
					que.offer(nxt);
//					System.out.println(Arrays.toString(cost));
//					System.out.println(Arrays.toString(ans));
				} else if (cost[nxt]==cost[cur]+1) {
					ans[nxt]=modadd(ans[nxt], ans[cur]);
//					System.out.println(Arrays.toString(cost));
//					System.out.println(Arrays.toString(ans));
				} else {
					continue;
				}
			}
		}
		System.out.println(ans[n]);
	}
	//abc065_c,abc211_c,abc211_d: valをMで割った余り
	static long mod(long val) {
		return val%M;
	}
	//abc211_c,abc211_d: val+xをMで割った余り
	static long modadd(long val, long x) {
		return mod(val+x);
	}
	static class Counter { //Counter_int_listint20250413
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
4 5
2 4
1 2
2 3
1 3
3 4

4 3
1 3
2 3
2 4

2 0

7 8
1 3
1 4
2 3
2 4
2 5
2 6
5 7
6 7
*/
