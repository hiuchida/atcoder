import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ary=new int[n];
		Counter cnt=new Counter();
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
			cnt.inc(ary[i]);
		}
//		System.out.println(cnt);
		long ans=0;
		for (int v : cnt.keySet()) {
			int c1=cnt.get(v);
			int c2=cnt.get(v-1);
			int c3=cnt.get(v+1);
			int c=c1+c2+c3;
			ans=Math.max(ans, c);
//			System.out.println(v+" "+c1+" "+c2+" "+c3+" "+c);
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
7
3 1 4 1 5 9 2

10
0 1 2 3 4 5 6 7 8 9

1
99999
*/
