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
		Deque<Que> que=new ArrayDeque<>();
		que.offer(new Que(1, 0));
		TreeSet<Integer> set=new TreeSet<>();
		while (que.size()>0) {
			Que b=que.poll();
//			System.out.println(b);
			if (set.contains(b.cur)) {
				System.out.println("No");
				System.exit(0);
			}
			set.add(b.cur);
			List<Integer> v=cnt.get(b.cur);
			for (int nxt : v) {
				if (nxt==b.pre) continue;
				que.offer(new Que(nxt, b.cur));
			}
		}
		if (set.size()!=n) {
			System.out.println("No");
			System.exit(0);
		}
		System.out.println("Yes");
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
