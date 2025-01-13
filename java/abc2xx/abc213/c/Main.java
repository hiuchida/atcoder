import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	void solve() {
		int h = nextInt();
		int w = nextInt();
		int n = nextInt();
		List<Point> list = new ArrayList<>();
		TreeMap<Integer,Integer> lx = new TreeMap<>();
		TreeMap<Integer,Integer> ly = new TreeMap<>();
		for (int i=0; i<n; i++) {
			int a = nextInt();
			int b = nextInt();
			list.add(new Point(a, b));
			lx.put(a, 0);
			ly.put(b, 0);
		}
		int i=1;
		for (int key : lx.keySet()) {
			lx.put(key, i);
			i++;
		}
		i=1;
		for (int key : ly.keySet()) {
			ly.put(key, i);
			i++;
		}
		DEBUG(list);
		DEBUG(lx);
		DEBUG(ly);
		for (i=0; i<n; i++) {
			Point p=list.get(i);
			int nx=lx.get(p.st);
			int ny=ly.get(p.ed);
			System.out.println(nx + " " + ny);
		}
	}
	long ans = 0;//Integer.MAX_VALUE;
	int n;
	long ln;
	String s;
	StringBuilder sb = new StringBuilder();
	List<String> list = new ArrayList<>();
	Deque<Integer> que = new ArrayDeque<>();
	TreeSet<Integer> set = new TreeSet<>();
	TreeMap<Integer,Integer> map = new TreeMap<>();
	int[] ary;
/*
4 5 2
3 2
2 5

1000000000 1000000000 10
1 1
10 10
100 100
1000 1000
10000 10000
100000 100000
1000000 1000000
10000000 10000000
100000000 100000000
1000000000 1000000000
*/

	static class Point implements Comparable<Point> {
		int st;
		int ed;
		Point(int st, int ed) {
			this.st = st;
			this.ed = ed;
		}
		@Override
		public int compareTo(Main.Point that) {
			int cmp = Integer.compare(this.st, that.st);
			if (cmp != 0) return cmp;
			return Integer.compare(this.ed, that.ed);
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
