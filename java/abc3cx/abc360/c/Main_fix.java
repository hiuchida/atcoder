import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		System.out.println(Arrays.toString(ary));
		int[] wary=new int[n];
		for (int i=0; i<n; i++) {
			wary[i]=sc.nextInt();
		}
		System.out.println(Arrays.toString(wary));
		List<Point> list = new ArrayList<>();
		Counter cnt = new Counter();
		for (int i=0; i<n; i++) {
			list.add(new Point(ary[i], wary[i]));
			cnt.inc(ary[i]);
		}
		Collections.sort(list);
		System.out.println(list);
		System.out.println(cnt);
		long ans=0;
		for (int i=0; i<n; i++) {
			Point p=list.get(i);
			int c=cnt.get(p.st);
			if (c>1) {
				cnt.dec(p.st);
				ans+=p.ed;
			}
		}
		System.out.println(ans);
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
	static class Point implements Comparable<Point> {
		final int st;
		final int ed;
		Point(int st, int ed) {
			this.st = st;
			this.ed = ed;
		}
		@Override
		public int compareTo(Main.Point that) {
			int cmp = Integer.compare(this.ed, that.ed);
			if (cmp != 0) return cmp;
			return Integer.compare(this.st, that.st);
		}
		@Override
		public int hashCode() {
			return Objects.hash(st, ed);
		}
		@Override
		public boolean equals(Object obj) {
			Point that = (Point) obj;
			return this.st == that.st && this.ed == that.ed;
		}
		@Override
		public String toString() {
			return "(" + st + "," + ed + ")";
		}
	}
}
/*
5
2 2 3 3 5
33 40 2 12 16

12
3 6 7 4 12 4 8 11 11 1 8 11
3925 9785 9752 3587 4013 1117 3937 7045 6437 6208 3391 6309
*/
