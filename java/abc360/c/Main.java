import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	void solve() {
		n = nextInt();
		ary = nextIntAry(n);
//		Arrays.sort(ary);
		DEBUG(ary);
		int[] wary = nextIntAry(n);
//		Arrays.sort(wary);
		DEBUG(wary);
		Counter cnt = new Counter();
		for (int i=0; i<n; i++) {
			list.add(new Point(ary[i], wary[i]));
			cnt.inc(ary[i]);
		}
		Collections.sort(list);
		DEBUG(list);
		DEBUG(cnt);
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
	long ans = 0;//Integer.MAX_VALUE;
	int n;
	long ln;
	String s;
	StringBuilder sb = new StringBuilder();
	List<Point> list = new ArrayList<>();
	Deque<Integer> que = new ArrayDeque<>();
	TreeSet<Integer> set = new TreeSet<>();
	TreeMap<Integer,Integer> map = new TreeMap<>();
	int[] ary;
/*
5
2 2 3 3 5
33 40 2 12 16

12
3 6 7 4 12 4 8 11 11 1 8 11
3925 9785 9752 3587 4013 1117 3937 7045 6437 6208 3391 6309
*/
	static class Counter {
		Map<Integer, Integer> map = new TreeMap<>();
		int size() {
			return map.size();
		}
		int get(int c) {
			Integer v = map.get(c);
			if (v == null) v = 0;
			return v;
		}
		void inc(int c) {
			int v = get(c);
			v++;
			map.put(c, v);
		}
		void dec(int c) {
			int v = get(c);
			if (v>0) v--;
			map.put(c, v);
		}
		void add(int c, int x) {
			int v = get(c);
			v += x;
			map.put(c, v);
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

	//---------------------------------------------------------------
	static Scanner sc;
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		new Main().solve();
	}
	static int nextInt() {
		return sc.nextInt();
	}
	static long nextLong() {
		return sc.nextLong();
	}
	static String next() {
		return sc.next();
	}
	static int[] nextIntAry(int n) {
		int[] ary = new int[n];
		for (int i=0; i<n; i++) {
			int a = nextInt();
			ary[i] = a;
		}
		return ary;
	}
	static TreeSet<Integer> nextIntSet(int n) {
		TreeSet<Integer> set = new TreeSet<>();
		for (int i=0; i<n; i++) {
			int a = nextInt();
			set.add(a);
		}
		return set;
	}
	static void DEBUG(Object x) {
		if (DEBUG) System.out.println(x);
	}
	static void DEBUG(long x) {
		if (DEBUG) DEBUG(""+x);
    }
	static void DEBUG(int[] x) {
		if (DEBUG) DEBUG(Arrays.toString(x));
    }
	static void DEBUG(long[] x) {
		if (DEBUG) DEBUG(Arrays.toString(x));
    }
	static void ok() {
		System.out.println("Yes");
		System.exit(0);
	}
	static void ng() {
		System.out.println("No");
		System.exit(0);
	}
}
