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
5
3 1 4 1 5

10
2 5 6 5 2 1 7 9 7 2
*/
