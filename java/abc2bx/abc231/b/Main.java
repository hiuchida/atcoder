import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Counter c=new Counter();
		for (int i=0; i<n; i++) {
			String s = sc.next();
			c.inc(s);
		}
		String ans="";
		int cnt=0;
		for (String s : c.keySet()) {
			int v=c.get(s);
			if (cnt<v) {
				ans=s;
				cnt=v;
			}
		}
		System.out.println(ans);
	}
	static class Counter {
		Map<String, Integer> map = new TreeMap<>();
		int size() {
			return map.size();
		}
		int get(String c) {
			Integer v = map.get(c);
			if (v == null) v = 0;
			return v;
		}
		void inc(String c) {
			int v = get(c);
			v++;
			map.put(c, v);
		}
		void dec(String c) {
			int v = get(c);
			v--;
			if (v==0) map.remove(c);
			else map.put(c, v);
		}
		void add(String c, int x) {
			int v = get(c);
			v += x;
			map.put(c, v);
		}
		void sub(String c, int x) {
			int v = get(c);
			v -= x;
			if (v==0) map.remove(c);
			else map.put(c, v);
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
snuke
snuke
takahashi
takahashi
takahashi

5
takahashi
takahashi
aoki
takahashi
snuke

1
a
*/
