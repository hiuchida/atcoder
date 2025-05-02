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
				Integer u=sc.nextInt();
				Integer v=sc.nextInt();
				cnt.add(u, v);
				cnt.add(v, u);
//				System.out.println(cnt);
			} else {
				Integer v=sc.nextInt();
				for (Integer u : cnt.get(v)) {
					cnt.del(u, v);
					if (cnt.get(u).size()==0) cnt.remove(u);
				}
				cnt.remove(v);
//				System.out.println(cnt);
			}
			int ans=n-cnt.size();
			System.out.println(ans);
		}
	}
	static class Counter { //unused Counter_int_setint250414
		Map<Integer, TreeSet<Integer>> map = new TreeMap<>();
		int size() {
			return map.size();
		}
		TreeSet<Integer> get(Integer k) {
			TreeSet<Integer> v = map.get(k);
			if (v == null) v = new TreeSet<>();
			return v;
		}
		void put(int k, TreeSet<Integer> v) {
			map.put(k, v);
		}
		void remove(Integer k) {
			map.remove(k);
		}
		boolean is(Integer k, Integer idx) {
			TreeSet<Integer> v = get(k);
			return v.contains(idx);
		}
		void add(Integer k, Integer idx) {
			TreeSet<Integer> v = get(k);
			v.add(idx);
			put(k, v);
		}
		void del(Integer k, Integer idx) {
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
