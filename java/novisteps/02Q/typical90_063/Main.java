import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int h=sc.nextInt();
		int w=sc.nextInt();
		Counter[] cnt=new Counter[h];
		for (int y=0; y<h; y++) {
			cnt[y]=new Counter();
			for (int x=0; x<w; x++) {
				int a=sc.nextInt();
				cnt[y].add(a, x);
			}
		}
//		for (int y=0; y<h; y++) {
//			System.out.println(cnt[y]);
//		}
		long ans=0;
		for (int i=0; i < 1 << h; i++) {
			List<Counter> list=new ArrayList<>();
			for (int j=0; j<h; j++) {
				int mask=1 << j;
				if ((i&mask)>0) {
					list.add(cnt[j]);
				}
			}
			long x=calc(list);
//			System.out.println(i+" "+x+" "+list);
			ans=Math.max(ans, x);
		}
		System.out.println(ans);
	}
	static long calc(List<Counter> list) {
		long ans=0;
		if (list.size()==0) return 0;
		if (list.size()==1) {
			for (int v : list.get(0).keySet()) {
				ans=Math.max(ans, list.get(0).size(v));
			}
			return ans;
		}
		for (int v : list.get(0).keySet()) {
			Counter cnt=new Counter();
			for (int j=0; j<list.size(); j++) {
				Counter c=list.get(j);
				for (int u : c.get(v)) {
					cnt.add(u, j);
				}
			}
			long x=0;
			for (int u : cnt.keySet()) {
				if (cnt.get(u).size()==list.size()) x++;
			}
			ans=Math.max(ans, x*list.size());
		}
		return ans;
	}
	static class Counter { //Counter_int_setint250502
		Map<Integer, TreeSet<Integer>> map = new TreeMap<>();
		int size() {
			return map.size();
		}
		int size(int k) {
			TreeSet<Integer> v = map.get(k);
			if (v == null) return 0;
			return v.size();
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
4 6
1 1 1 1 1 2
1 2 2 2 2 2
1 2 2 3 2 3
1 2 3 2 2 3

3 3
1 2 3
4 5 6
7 8 9

5 3
7 7 7
7 7 7
7 7 7
7 7 7
7 7 7
*/
