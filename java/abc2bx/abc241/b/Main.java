import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] aa=new int[n];
		for (int i=0; i<n; i++) {
			aa[i]=sc.nextInt();
		}
		int[] ab=new int[m];
		for (int i=0; i<m; i++) {
			ab[i]=sc.nextInt();
		}
		Counter c=new Counter();
		for (int i=0; i<n; i++) {
			c.inc(aa[i]);
		}
		for (int i=0; i<m; i++) {
			int b=ab[i];
			if (c.get(b)>0) {
				c.dec(b);
			} else {
				System.out.println("No");
				System.exit(0);
			}
		}
		System.out.println("Yes");
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
3 2
1 1 3
3 1

1 1
1000000000
1

5 2
1 2 3 4 5
5 5
*/
