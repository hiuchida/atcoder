import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		char[] ary=s.toCharArray();
		int n=s.length();
		int x=0;
		Counter cnt=new Counter();
		cnt.inc(x);
		for (int i=0; i<n; i++) {
			int v=ary[i]-'0';
			int b=1 << v;
			x ^= b;
			cnt.inc(x);
//			System.out.println(String.format("%03x", x));
		}
//		System.out.println(cnt);
		long ans=0;
		for (int key : cnt.keySet()) {
			int v=cnt.get(key);
			ans+=(long)v*(v-1)/2;
		}
		System.out.println(ans);
	}
	static class Counter { //Counter_int_int20250416
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
		public int hashCode() {
			return Objects.hash(map);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (obj == null) return false;
			if (getClass() != obj.getClass()) return false;
			Counter other = (Counter) obj;
			return Objects.equals(map, other.map);
		}
		@Override
		public String toString() {
			return map.toString();
		}
	}
}
/*
20230322

0112223333444445555556666666777777778888888889999999999

3141592653589793238462643383279502884197169399375105820974944
*/
/*
01234567899876543210
*/
