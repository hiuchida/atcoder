import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		Counter cnt=new Counter();
		for (int i=1; i<=n; i++) {
			int a=sc.nextInt();
			int b=sc.nextInt();
			cnt.add(a, b);
			cnt.add(b, a);
		}
		int ans=1;
		Deque<Integer> que=new ArrayDeque<>();
		Set<Integer> set=new HashSet<>();
		que.offer(1);
		set.add(1);
		while (que.size()>0) {
			int i=que.poll();
			ans=Math.max(ans, i);
			List<Integer> v=cnt.get(i);
			for (int nxt : v) {
				if (set.contains(nxt)) continue;
				set.add(nxt);
				que.offer(nxt);
			}
		}
		System.out.println(ans);
	}
	static class Counter {
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
		void add(int k, int idx) {
			List<Integer> v = get(k);
			v.add(idx);
			put(k, v);
		}
		void remove(int k) {
			map.remove(k);
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
4
1 4
4 3
4 10
8 3

6
1 3
1 5
1 12
3 5
3 12
5 12

3
500000000 600000000
600000000 700000000
700000000 800000000
*/
