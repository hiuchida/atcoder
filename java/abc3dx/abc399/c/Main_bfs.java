import java.util.*;
public class Main {
	static Counter cnt=new Counter();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		for (int i=0; i<m; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			cnt.add(u, v);
			cnt.add(v, u);
		}
		boolean[] flag=new boolean[n+1];
		int grp=0;
		for (int i=1; i<=n; i++) {
			if (!flag[i]) {
				Deque<Que> que=new ArrayDeque<>();
				que.offer(new Que(i));
				flag[i]=true;
				while (que.size()>0) {
					Que b=que.poll();
					for (int nxt : cnt.get(b.cur)) {
						if (!flag[nxt]) {
							que.offer(new Que(nxt));
							flag[nxt]=true;
						}
					}
				}
				grp++;
			}
		}
//		System.out.println(grp);
		int ans=m-(n-grp);
		System.out.println(ans);
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
}
/*
4 4
1 2
1 3
2 4
3 4

5 0

10 10
7 9
4 6
6 10
2 5
5 6
5 9
6 8
4 8
1 5
1 4
*/
