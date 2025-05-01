import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		final int M=46;
		int n=sc.nextInt();
//		int[][] ary=new int[3][n];
		Counter[] cnt=new Counter[3];
		for (int j=0; j<3; j++) {
			cnt[j]=new Counter();
			for (int i=0; i<n; i++) {
				int a=sc.nextInt()%M;
//				ary[j][i]=a;
				cnt[j].inc(a);
			}
		}
//		for (int j=0; j<3; j++) {
//			System.out.println(Arrays.toString(ary[j]));
//			System.out.println(cnt[j]);
//		}
		long ans=0;
		for (int a : cnt[0].keySet()) {
			for (int b : cnt[1].keySet()) {
				for (int c : cnt[2].keySet()) {
					int abc=a+b+c;
					if (abc%M==0) {
						long x=cnt[0].get(a);
						x*=cnt[1].get(b);
						x*=cnt[2].get(c);
						ans+=x;
					}
				}
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
3
10 13 93
5 27 35
55 28 52

3
10 56 102
16 62 108
20 66 112

20
238 395 46 238 264 114 354 52 324 14 472 64 307 280 209 24 165 194 179 248
270 83 377 332 173 21 362 75 66 342 229 117 124 481 48 235 376 13 420 74
175 427 76 278 486 169 311 47 348 225 41 482 355 356 263 95 170 156 340 289
*/
