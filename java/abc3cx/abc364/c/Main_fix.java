import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	void solve() {
		n = nextInt();
		long x = nextLong();
		long y = nextLong();
		aa = nextIntAry(n);
		ab = nextIntAry(n);
		long sa = 0;
		long sb = 0;
		for (int i=0; i<n; i++) {
			list.add(new Point(aa[i], ab[i]));
			sa += aa[i];
			sb += ab[i];
		}
		if (sa <= x && sb <= y) {
			System.out.println(n);
			System.exit(0);
		}
		Arrays.sort(aa);
		Arrays.sort(ab);
		DEBUG(aa);
		DEBUG(ab);
		Collections.sort(list, new Comparator<Point>() {
			@Override
			public int compare(Point p1, Point p2) {
				return -Integer.compare(p1.st, p2.st);
			}
			
		});
		DEBUG(list);
		sa = 0;
		sb = 0;
		for (int i=0; i<n; i++) {
			sa += list.get(i).st;
			sb += list.get(i).ed;
			if (sa > x || sb > y) ans = Math.min(ans, i+1);
		}
		Collections.sort(list, new Comparator<Point>() {
			@Override
			public int compare(Point p1, Point p2) {
				return -Integer.compare(p1.ed, p2.ed);
			}
			
		});
		DEBUG(list);
		sa = 0;
		sb = 0;
		for (int i=0; i<n; i++) {
			sa += list.get(i).st;
			sb += list.get(i).ed;
			if (sa > x || sb > y) ans = Math.min(ans, i+1);
		}
		System.out.println(ans);
	}
	long ans = Integer.MAX_VALUE;
	int n;
	long ln;
	String s;
	StringBuilder sb = new StringBuilder();
	List<Point> list = new ArrayList<>();
	Deque<Integer> que = new ArrayDeque<>();
	TreeSet<Integer> set = new TreeSet<>();
	TreeMap<Integer,Integer> map = new TreeMap<>();
	int[] aa;
	int[] ab;
/*
3 1234 abc
4 7 3
5 6 2
*/

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
	static class Point implements Comparable<Point> {
		final int st;
		final int ed;
		Point(int st, int ed) {
			this.st = st;
			this.ed = ed;
		}
		@Override
		public int compareTo(Main.Point that) {
			int cmp = Integer.compare(this.st+this.ed, that.st+that.ed);
			return cmp;
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
