import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		Counter c=new Counter();
		for (int i=0; i<m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			c.inc(a);
			c.inc(b);
		}
		for (int k : c.keySet()) {
			int v=c.get(k);
			if (v>2) ng();
		}
		if (n==m) ng();
		System.out.println("Yes");
	}
	static void ng() {
		System.out.println("No");
		System.exit(0);
	}
	static class Counter {
		Map<Integer, Integer> map = new TreeMap<>();
		int size() {
			return map.size();
		}
		int get(int c) {
			Integer v = map.get(c);
			if (v == null) v = 0;
			return v;
		}
		void inc(int c) {
			int v = get(c);
			v++;
			map.put(c, v);
		}
		void dec(int c) {
			int v = get(c);
			v--;
			if (v==0) map.remove(c);
			else map.put(c, v);
		}
		void add(int c, int x) {
			int v = get(c);
			v += x;
			map.put(c, v);
		}
		void sub(int c, int x) {
			int v = get(c);
			v -= x;
			if (v==0) map.remove(c);
			else map.put(c, v);
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
4 2
1 3
2 3

4 3
1 4
2 4
3 4
*/
/*
3 3
1 2
1 3
2 3
*/
