import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int d=sc.nextInt();
		Counter cnt=new Counter();
		for (int i=0; i<n; i++) {
			int a=sc.nextInt();
			cnt.inc(a);
		}
//		System.out.println(cnt);
		List<Integer> list=new ArrayList<>(cnt.keySet());
		long ans=0;
		for (int key : list) {
//			System.out.println(cnt+" "+key+" ans="+ans);
			long v1=cnt.get(key);
			if (v1==0) continue;
			cnt.put(key, 0);
			long v2=cnt.get(key+d);
			if (v2==0) {
				cnt.put(key, 0);
				continue;
			}
			cnt.put(key+d, 0);
			for (int i=2; true; i+=2) {
				long v11=cnt.get(key+i*d);
				if (v11==0) break;
				cnt.put(key+i*d, 0);
				v1+=v11;
				long v21=cnt.get(key+(i+1)*d);
				if (v21==0) break;
				cnt.put(key+(i+1)*d, 0);
				v2+=v21;
			}
			if (v1<v2) {
				ans+=v1;
			} else {
				ans+=v2;
			}
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
3 1 4 1 5

4 3
1 6 1 8

10 3
1 6 2 10 2 3 2 10 6 4
*/
/*
6 2
1 3 3 5 5 7

7 2
1 3 3 3 5 5 7

7 2
1 3 3 5 5 5 7
*/
