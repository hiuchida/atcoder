import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		Counter cnt=new Counter();
		for (int i=0; i<m; i++) {
			int a=sc.nextInt();
			int b=sc.nextInt();
			cnt.add(a, b);
			cnt.add(b, a);
		}
		Deque<Bean> que=new ArrayDeque<>();
		int[] flag=new int[n+1];
		int[] prev=new int[n+1];
		Arrays.fill(flag, Integer.MAX_VALUE);
		que.offer(new Bean(1, 0, 0));
		while (que.size()>0) {
			Bean b=que.poll();
//			System.out.println(b);
			if (flag[b.c]<b.s) continue;
			flag[b.c]=b.s;
			prev[b.c]=b.p;
			for (int nxt : cnt.get(b.c)) {
				if (flag[nxt]>b.s+1) {
					que.offer(new Bean(nxt, b.c, b.s+1));
				}
			}
		}
//		System.out.println(Arrays.toString(flag));
//		System.out.println(Arrays.toString(prev));
		for (int i=2; i<=n; i++) {
			if (flag[i]==Integer.MAX_VALUE) {
				System.out.println("No");
				System.exit(0);
			}
		}
		System.out.println("Yes");
		for (int i=2; i<=n; i++) {
			System.out.println(prev[i]);
		}
	}
	static class Bean { //Bean_int20250410
		int c;
		int p;
		int s;
		Bean(int c, int p, int s) {
			this.c=c;
			this.p=p;
			this.s=s;
		}
		void add(int n) {
			c+=n;
		}
		@Override
		public String toString() {
			return "(" + c + "," + p + "," + s + ")";
		}
	}
	static class Counter { //Counter_list20250410
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
2 3
3 4
4 2

6 9
3 4
6 1
2 4
5 3
4 6
1 5
6 2
4 5
5 6
*/
