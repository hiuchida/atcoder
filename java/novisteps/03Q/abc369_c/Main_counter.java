import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary = new int[n+1];
		for (int i=1; i<=n; i++) {
			int a = sc.nextInt();
			ary[i]=a;
		}
		int[] dif = new int[n+2];
		dif[n+1] = Integer.MAX_VALUE;
		for (int i=1; i<=n; i++) {
			dif[i]=ary[i]-ary[i-1];
		}
		Counter cnt = new Counter();
		for (int i=2; i<n+1; i++) {
			if (dif[i]==dif[i+1]) {
				int c=0;
				int j=i+1;
				for (; j<=n; j++) {
					if (dif[i]==dif[j])
						c=j-i+1;
					else
						break;
				}
				i=j-1;
				cnt.inc(c);
			} else {
				cnt.inc(1);
			}
		}
//		System.out.println(Arrays.toString(ary));
//		System.out.println(Arrays.toString(dif));
//		System.out.println(map);
		long ans = 0;
		ans += n;
		for (int c : cnt.keySet()) {
			int v=cnt.get(c);
			long nc = calc(1, c);
			ans += nc*v;
		}
		System.out.println(ans);
	}
	static long calc(int a, int b) { //abc181_b,abc369_c: 初項a、末項bの等差数列の和
		long n=b-a+1;
		long ans=n*(a+b)/2;
		return ans;
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
1
2

2
2 4

2
1 4

3
2 4 6
*/
