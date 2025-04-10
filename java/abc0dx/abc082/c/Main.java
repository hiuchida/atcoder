import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ary=new int[n];
		Counter cnt=new Counter();
		for (int i=0; i<n; i++) {
			int a=sc.nextInt();
			ary[i]=a;
			cnt.inc(ary[i]);
		}
//		System.out.println(Arrays.toString(ary));
//		System.out.println(cnt);
		long ans=0;
		for (int k : cnt.keySet()) {
			int v=cnt.get(k);
			if (v<k) ans+=v;
			else if (v>k) ans+=v-k;
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
4
3 3 3 3

5
2 4 1 4 2

6
1 2 2 3 3 3

1
1000000000

8
2 7 1 8 2 8 1 8
*/
