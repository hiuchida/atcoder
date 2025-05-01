import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int q=sc.nextInt();
		Counter cnt=new Counter();
		for (int qq=0; qq<q; qq++) {
			int c=sc.nextInt();
			if (c==1) {
				int u=sc.nextInt();
				int v=sc.nextInt();
				cnt.add(u, v);
				cnt.add(v, u);
//				System.out.println(cnt);
			} else {
				int v=sc.nextInt();
				for (int u : cnt.get(v)) {
					cnt.del(u, v);
				}
				cnt.remove(v);
//				System.out.println(cnt);
			}
			int ans=n-calc(cnt);
			System.out.println(ans);
		}
	}
	static int calc(Counter cnt) {
		int ans=0;
		for (int u : cnt.keySet()) {
			if (cnt.get(u).size()>0) ans++;
		}
		return ans;
	}
	static class Counter { //Counter_int_setint250414
		Map<Integer, TreeSet<Integer>> map = new TreeMap<>();
		int size() {
			return map.size();
		}
		TreeSet<Integer> get(int k) {
			TreeSet<Integer> v = map.get(k);
			if (v == null) v = new TreeSet<>();
			return v;
		}
		void put(int k, TreeSet<Integer> v) {
			map.put(k, v);
		}
		void remove(int k) {
			map.remove(k);
		}
		boolean is(int k, int idx) {
			TreeSet<Integer> v = get(k);
			return v.contains(idx);
		}
		void add(int k, int idx) {
			TreeSet<Integer> v = get(k);
			v.add(idx);
			put(k, v);
		}
		void del(int k, int idx) {
			TreeSet<Integer> v = get(k);
			v.remove(idx);
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
3 7
1 1 2
1 1 3
1 2 3
2 1
1 1 2
2 2
1 1 2

2 1
2 1
*/
