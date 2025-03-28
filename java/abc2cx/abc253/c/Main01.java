import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int q = sc.nextInt();
		Counter cnt=new Counter();
		for (int i=0; i<q; i++) {
			int cmd = sc.nextInt();
			if (cmd==1) {
				int x = sc.nextInt();
				cnt.inc(x);
//				System.out.println(cnt);
			} else if (cmd==2) {
				int x = sc.nextInt();
				int c = sc.nextInt();
				int v=cnt.get(x);
				int del=Math.min(c, v);
				cnt.sub(x, del);
//				System.out.println(cnt);
			} else {
				TreeSet<Integer> set=new TreeSet<Integer>(cnt.map.keySet());
				int min=set.first();
				int max=set.last();
				System.out.println(max-min);
			}
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
8
1 3
1 2
3
1 2
1 7
3
2 2 3
3

4
1 10000
1 1000
2 100 3
1 10
*/
