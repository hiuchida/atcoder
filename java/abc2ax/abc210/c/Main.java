import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int k=sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		Counter cnt=new Counter();
		for (int i=0; i<k; i++) {
			cnt.inc(ary[i]);
		}
//		System.out.println(cnt);
		long ans=0;
		ans=Math.max(ans, cnt.size());
		for (int i=k; i<n; i++) {
			cnt.dec(ary[i-k]);
			cnt.inc(ary[i]);
//			System.out.println(i+" "+cnt+" "+cnt.size());
			ans=Math.max(ans, cnt.size());
		}
		System.out.println(ans);
	}
	static class Counter { //Counter_int_int20250410
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
7 3
1 2 1 2 3 3 1

5 5
4 4 4 4 4

10 6
304621362 506696497 304621362 506696497 834022578 304621362 414720753 304621362 304621362 414720753
*/
