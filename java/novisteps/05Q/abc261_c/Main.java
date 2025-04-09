import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Counter cnt=new Counter();
		for (int i=0; i<n; i++) {
			String s = sc.next();
			int v=cnt.get(s);
			if (v==0) System.out.println(s);
			else System.out.println(s+"("+v+")");
			cnt.inc(s);
		}
	}
	static class Counter {
		Map<String, Integer> map = new TreeMap<>();
		int size() {
			return map.size();
		}
		int get(String k) {
			Integer v = map.get(k);
			if (v == null) v = 0;
			return v;
		}
		void put(String k, int v) {
			if (v==0) map.remove(k);
			else map.put(k, v);
		}
		void inc(String k) {
			int v = get(k);
			v++;
			put(k, v);
		}
		void dec(String k) {
			int v = get(k);
			v--;
			put(k, v);
		}
		void add(String k, int x) {
			int v = get(k);
			v += x;
			put(k, v);
		}
		void sub(String k, int x) {
			int v = get(k);
			v -= x;
			put(k, v);
		}
		Set<String> keySet() {
			return map.keySet();
		}
		@Override
		public String toString() {
			return map.toString();
		}
	}
}
/*
5
newfile
newfile
newfolder
newfile
newfolder

11
a
a
a
a
a
a
a
a
a
a
a
*/
