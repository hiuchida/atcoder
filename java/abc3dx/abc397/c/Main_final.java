import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary=new int[n];
		Counter c1=new Counter();
		for (int i=0; i<n; i++) {
			int a = sc.nextInt();
			ary[i]=a;
			c1.inc(a);
		}
		Counter c2=new Counter();
		int ans=0;
		for (int i=0; i<n; i++) {
			int a=ary[i];
			c2.inc(a);
			c1.dec(a);
			ans=Math.max(ans, c1.size()+c2.size());
		}
		System.out.println(ans);
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
5
3 1 4 1 5

10
2 5 6 5 2 1 7 9 7 2
*/
