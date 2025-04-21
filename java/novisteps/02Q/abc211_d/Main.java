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
		Deque<Que> que=new ArrayDeque<>();
		que.offer(new Que(1));
		while (que.size()>0) {
			Que b=que.poll();
//			System.out.println(cur);
			for (int nxt : cnt.get(b.cur)) {
				if (cost[nxt]<0) {
					cost[nxt]=cost[b.cur]+1;
					ans[nxt]=ans[b.cur];
					que.offer(new Que(nxt));
//					System.out.println(Arrays.toString(cost));
//					System.out.println(Arrays.toString(ans));
				} else if (cost[nxt]==cost[b.cur]+1) {
					ans[nxt]=modadd(ans[nxt], ans[b.cur]);
//					System.out.println(Arrays.toString(cost));
//					System.out.println(Arrays.toString(ans));
				} else {
					continue;
				}
			}
		}
		System.out.println(ans[n]);
	}
	//valをMで割った余り
	static long mod(long val) { //ModFunc20250421
		return val%M;
	}
	//val+xをMで割った余り
	static long modadd(long val, long x) { //ModFunc20250421
		return mod(val+x);
	}
	static class Que { //Que_cur20250416
		int cur;
		Que(int cur) {
			this.cur=cur;
		}
		@Override
		public String toString() {
			return "(" + cur + ")";
		}
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
