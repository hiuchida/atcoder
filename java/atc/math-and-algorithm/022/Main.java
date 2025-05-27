import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		Counter cnt=new Counter();
		for (int i=0; i<n; i++) {
			int a=sc.nextInt();
			cnt.inc(a);
		}
//		System.out.println(cnt);
		long ans=0;
		List<Integer> keys=new ArrayList<>(cnt.keySet());
		for (int key : keys) {
			int c1=cnt.get(key);
			if (c1==0) continue;
			int key2=100000-key;
			if (key==key2) {
				ans+=calc_c(c1, 2);
//				System.out.println(ans+" "+key+" "+c1);
			} else {
				int c2=cnt.get(key2);
				if (c2==0) continue;
				ans+=(long)c1*c2;
//				System.out.println(ans+" "+key+" "+c1+" "+c2);
			}
			cnt.sub(key, c1);
		}
		System.out.println(ans);
	}
	static long calc_c(int n, int k) {
		long ans=1;
		for (int i=0; i<k; i++) {
			ans*=n-i;
		}
		for (int i=1; i<=k; i++) {
			ans/=i;
		}
		return ans;
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
6
40000 50000 20000 80000 50000 30000
*/
