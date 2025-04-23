import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int q=sc.nextInt();
		Counter[] cnt=new Counter[n];
		for (int i=0; i<n; i++) cnt[i]=new Counter();
		Counter2 cnt2=new Counter2();
		for (int qq=0; qq<q; qq++) {
			int cmd=sc.nextInt();
			int i=sc.nextInt();
			switch (cmd) {
			case 1:
				int j=sc.nextInt();
				cnt[j-1].inc(i);
				cnt2.add(i, j);
//				System.out.println(Arrays.toString(cnt));
				break;
			case 2:
				Counter c=cnt[i-1];
				for (int key : c.keySet()) {
					for (int k=0; k<c.get(key); k++) {
						System.out.print(key+" ");
					}
				}
				System.out.println();
				break;
			case 3:
				for (int v : cnt2.get(i)) {
					System.out.print(v+" ");
				}
				System.out.println();
				break;
			}
		}
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
	static class Counter2 { //Counter_int_setint250414
		Map<Integer, TreeSet<Integer>> map = new TreeMap<>();
		int size() {
			return map.size();
		}
		TreeSet<Integer> get(int k) {
			TreeSet<Integer> v = map.get(k);
			if (v == null) v = new TreeSet<>();
			return v;
		}
		void put(int k, TreeSet<Integer> v) {
			map.put(k, v);
		}
		void remove(int k) {
			map.remove(k);
		}
		boolean is(int k, int idx) {
			TreeSet<Integer> v = get(k);
			return v.contains(idx);
		}
		void add(int k, int idx) {
			TreeSet<Integer> v = get(k);
			v.add(idx);
			put(k, v);
		}
		void del(int k, int idx) {
			TreeSet<Integer> v = get(k);
			v.remove(idx);
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
5
8
1 1 1
1 2 4
1 1 4
2 4
1 1 4
2 4
3 1
3 2

1
5
1 1 1
1 2 1
1 200000 1
2 1
3 200000
*/
