import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary=new int[n];
		TreeSet<Integer> set=new TreeSet<>();
		Counter cnt=new Counter();
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
			set.add(ary[i]);
			cnt.inc(ary[i]);
		}
//		System.out.println(Arrays.toString(ary));
//		System.out.println(set);
//		System.out.println(cnt);
		List<Integer> list=new ArrayList<>(set);
		TreeMap<Integer,Integer> map=new TreeMap<>();
		for (int i=0; i<list.size(); i++) {
			int v=list.size()-1-i;
			map.put(v, list.get(i));
		}
//		System.out.println(map);
		for (int i=0; i<n; i++) {
			Integer v=map.get(i);
			if (v==null) v=0;
			int c=cnt.get(v);
			System.out.println(c);
		}
	}
	static class Counter {
		Map<Integer, Integer> map = new TreeMap<>();
		int size() {
			return map.size();
		}
		int get(int k) {
			Integer v = map.get(k);
			if (v == null) v = 0;
			return v;
		}
		void put(int k, int v) {
			if (v==0) map.remove(k);
			else map.put(k, v);
		}
		void inc(int k) {
			int v = get(k);
			v++;
			put(k, v);
		}
		void dec(int k) {
			int v = get(k);
			v--;
			put(k, v);
		}
		void add(int k, int x) {
			int v = get(k);
			v += x;
			put(k, v);
		}
		void sub(int k, int x) {
			int v = get(k);
			v -= x;
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
6
2 7 1 8 2 8

1
1

10
979861204 57882493 979861204 447672230 644706927 710511029 763027379 710511029 447672230 136397527
*/
