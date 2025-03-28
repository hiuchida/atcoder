import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long k = sc.nextLong();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			int a = sc.nextInt();
			ary[i]=a;
		}
		long[] sum=new long[n+1];
		for (int i=0; i<n; i++) {
			sum[i+1]=sum[i]+ary[i];
		}
//		System.out.println(Arrays.toString(ary));
//		System.out.println(Arrays.toString(sum));
		long ans=0;
		Counter c=new Counter();
		for (int r=1; r<=n; r++) {
			c.inc(sum[r-1]);
			ans += c.get(sum[r]-k);
		}
		System.out.println(ans);
	}
	static class Counter {
		Map<Long, Integer> map = new TreeMap<>();
		int size() {
			return map.size();
		}
		int get(long c) {
			Integer v = map.get(c);
			if (v == null) v = 0;
			return v;
		}
		void inc(long c) {
			int v = get(c);
			v++;
			map.put(c, v);
		}
		void dec(long c) {
			int v = get(c);
			v--;
			if (v==0) map.remove(c);
			else map.put(c, v);
		}
		void add(long c, int x) {
			int v = get(c);
			v += x;
			map.put(c, v);
		}
		void sub(long c, int x) {
			int v = get(c);
			v -= x;
			if (v==0) map.remove(c);
			else map.put(c, v);
		}
		Set<Long> keySet() {
			return map.keySet();
		}
		@Override
		public String toString() {
			return map.toString();
		}
	}
}
/*
6 5
8 -3 5 7 0 -4

2 -1000000000000000
1000000000 -1000000000
*/
