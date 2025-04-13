import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		TreeSet<Integer> set=new TreeSet<>();
		Counter cnt=new Counter();
		for (int i=0; i<n; i++) {
			int a=sc.nextInt();
			set.add(a);
			cnt.inc(a);
		}
//		System.out.println(set);
//		System.out.println(cnt);
		long ans=set.size();
		ans*=set.size()-1;
		ans*=set.size()-2;
		ans/=6;
		for (int k : cnt.keySet()) {
			int x=cnt.get(k);
			ans*=x;
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
3 1 4 1

10
99999 99998 99997 99996 99995 99994 99993 99992 99991 99990

15
3 1 4 1 5 9 2 6 5 3 5 8 9 7 9
*/
