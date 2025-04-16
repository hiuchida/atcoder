import java.util.*;
public class Main {
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
		for (int i=1; i<=n; i++) {
			int v=cnt.get(i).size();
			if (v==0 || v>2) {
				System.out.println("No");
				System.exit(0);
			}
		}
		boolean[] flag=new boolean[n];
		Deque<Bean> que=new ArrayDeque<>();
		flag[1]=true;
		que.offer(new Bean(1, 0));
		TreeSet<Integer> set=new TreeSet<>();
		while (que.size()>0) {
			Bean b=que.poll();
//			System.out.println(b);
			if (set.contains(b.v)) {
				System.out.println("No");
				System.exit(0);
			}
			set.add(b.v);
			List<Integer> v=cnt.get(b.v);
			for (int nxt : v) {
				if (nxt==b.idx) continue;
				que.offer(new Bean(nxt, b.v));
			}
		}
		if (set.size()!=n) {
			System.out.println("No");
			System.exit(0);
		}
		System.out.println("Yes");
	}
	static class Bean { //Bean_int20250410
		int v;
		int idx;
		Bean(int v, int idx) {
			this.v=v;
			this.idx=idx;
		}
		void add(int n) {
			v+=n;
		}
		@Override
		public String toString() {
			return "(" + v + "," + idx + ")";
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
4 3
1 3
4 2
3 2

2 0

5 5
1 2
2 3
3 4
4 5
5 1
*/
