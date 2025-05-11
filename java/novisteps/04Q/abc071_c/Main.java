import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		final int N=2;
		int n=sc.nextInt();
		Counter cnt=new Counter();
		for (int i=0; i<n; i++) {
			int a=sc.nextInt();
			cnt.inc(a);
		}
		int[] ary=new int[N];
		int idx=0;
		long ans2=0;
		while (idx<N) {
//			System.out.println(cnt);
			if (cnt.size()==0) {
				long ans=Math.max(0, ans2);
				System.out.println(ans);
				System.exit(0);
			}
			Integer key=cnt.keySet().last();
			int val=cnt.get(key);
			if (val>=4) {
				long x=key;
				x*=x;
				ans2=Math.max(ans2, x);
			}	
			if (val>=2) {
				ary[idx++]=key;
			}
			cnt.sub(key, val);
		}
//		System.out.println(Arrays.toString(ary));
		long ans1=ary[0];
		ans1*=ary[1];
		long ans=Math.max(ans1, ans2);
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
6
3 1 2 4 2 1

4
1 2 3 4

10
3 3 3 3 4 4 4 5 5 5
*/
/*
4
9 9 9 9

8
40 40 40 40 1 1 100 100

8
10 10 10 10 1 1 200 200

4
999999999 999999999 1000000000 1000000000

4
1000000000 1000000000 1000000000 1000000000
*/
