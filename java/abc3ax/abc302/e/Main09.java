import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int q=sc.nextInt();
		Counter cnt=new Counter();
		int ans=n;
		for (int qq=0; qq<q; qq++) {
			int c=sc.nextInt();
			if (c==1) {
				Integer u=sc.nextInt();
				Integer v=sc.nextInt();
				if (cnt.size(u)==0) ans--;
				if (cnt.size(v)==0) ans--;
				cnt.add(u, v);
				cnt.add(v, u);
//				System.out.println(cnt);
			} else {
				Integer v=sc.nextInt();
				for (Integer u : cnt.get(v)) {
					cnt.del(u, v);
					if (cnt.size(u)==0) ans++;
				}
				if (cnt.size(v)>0) ans++;
				cnt.remove(v);
//				System.out.println(cnt);
			}
			System.out.println(ans);
		}
	}
	static class Counter { //Counter_int_setint250502
		Map<Integer, HashSet<Integer>> map = new HashMap<>();
		int size() {
			return map.size();
		}
		int size(Integer k) {
			HashSet<Integer> v = map.get(k);
			if (v == null) return 0;
			return v.size();
		}
		HashSet<Integer> get(Integer k) {
			HashSet<Integer> v = map.get(k);
			if (v == null) v = new HashSet<>();
			return v;
		}
		void put(int k, HashSet<Integer> v) {
			map.put(k, v);
		}
		void remove(Integer k) {
			map.remove(k);
		}
		boolean is(Integer k, Integer idx) {
			HashSet<Integer> v = get(k);
			return v.contains(idx);
		}
		void add(Integer k, Integer idx) {
			HashSet<Integer> v = get(k);
			v.add(idx);
			put(k, v);
		}
		void del(Integer k, Integer idx) {
			HashSet<Integer> v = get(k);
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
