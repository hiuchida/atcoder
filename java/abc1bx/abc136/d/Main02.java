import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		int n=s.length();
		char[] ary=s.toCharArray();
		Counter cnt=new Counter();
		for (int i=0; i<n; i++) {
			cnt.inc(i);
		}
		int loop=n;
		if (loop%2==1) loop++;
		for (int j=0; j<loop; j++) {
			Counter cnt2=new Counter();
			for (int i : cnt.keySet()) {
				int v=cnt.get(i);
				if (ary[i]=='L') cnt2.add(i-1, v);
				else cnt2.add(i+1, v);
			}
			cnt=cnt2;
//			System.out.println(cnt);
		}
		for (int i=0; i<n; i++) {
			System.out.print(cnt.get(i)+" ");
		}
		System.out.println();
	}
	static class Counter { //Counter_int_int20250413
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
		NavigableSet<Integer> keySet() {
			return (NavigableSet<Integer>) map.keySet();
		}
		@Override
		public String toString() {
			return map.toString();
		}
	}
}
/*
RRLRL

RRLLLLRLRRLL

RRRLLRLLRRRLLLLL
*/
