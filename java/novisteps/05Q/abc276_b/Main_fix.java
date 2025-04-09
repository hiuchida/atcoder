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
		for (int i=1; i<=n; i++) {
			List<Integer> v=cnt.get(i);
			Collections.sort(v);
			System.out.print(v.size());
			for (int vv : v) {
				System.out.print(" "+vv);
			}
			System.out.println();
		}
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
6 6
3 6
1 3
5 6
2 5
1 2
1 6

5 10
1 2
1 3
1 4
1 5
2 3
2 4
2 5
3 4
3 5
4 5
*/
