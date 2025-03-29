import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		Counter cnt=new Counter();
		int[] indeg=new int[n+1];
		for (int i=0; i<m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			cnt.add(a, b);
			indeg[b]++;
		}
//		System.out.println(cnt);
//		System.out.println(Arrays.toString(indeg));
		PriorityQueue<Integer> que=new PriorityQueue<>();
		for (int i=1; i<=n; i++) {
			if (indeg[i]==0) que.offer(i);
		}
		List<Integer> list=new ArrayList<>();
		while (que.size()>0) {
			int x=que.poll();
			list.add(x);
			List<Integer> set=cnt.get(x);
			for (int nxt : set) {
				indeg[nxt]--;
				if (indeg[nxt]==0) {
					que.offer(nxt);
				}
			}
		}
		if (list.size()!=n) {
			System.out.println(-1);
			System.exit(0);
		}
		for (int i=0; i<list.size(); i++) {
			System.out.print(list.get(i)+" ");
		}
		System.out.println();
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
4 3
2 1
3 4
2 4

2 3
1 2
1 2
2 1
*/
/*
9 3
3 4
4 5
5 2

9 3
1 2
1 2
1 2
*/
