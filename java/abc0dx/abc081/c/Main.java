import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int k=sc.nextInt();
		Counter cnt=new Counter();
		for (int i=0; i<n; i++) {
			int a=sc.nextInt();
			cnt.inc(a);
		}
//		System.out.println(cnt);
		if (cnt.size()<=k) {
			System.out.println(0);
			System.exit(0);
		}
		int[] ary=new int[cnt.size()];
		int idx=0;
		for (int key : cnt.keySet()) {
			ary[idx++]=cnt.get(key);
		}
		Arrays.sort(ary);
//		System.out.println(Arrays.toString(ary));
		long ans=0;
		for (int i=0; i<ary.length-k; i++) {
			ans+=ary[i];
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
5 2
1 1 2 2 5

4 4
1 1 2 2

10 3
5 1 3 2 4 1 1 2 3 4
*/
