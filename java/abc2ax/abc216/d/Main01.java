import java.util.*;
public class Main {
	static final boolean DEBUG = true;
	List<Deque<Point>> list = new ArrayList<>();
	TreeSet<Point> set = new TreeSet<>();
	void solve() {
		n = nextInt();
		int m = nextInt();
		for (int i=0; i<m; i++) {
			Deque<Point> que = new ArrayDeque<>();
			int k = nextInt();
			for (int j=0; j<k; j++) {
				int a = nextInt();
				que.offer(new Point(a, i));
			}
			list.add(que);
		}
//		System.out.println(list);
		boolean[] flag = new boolean[m];
		while (n>0) {
			boolean bhit=false;
			for (int i=0; i<m; i++) {
				if (!flag[i]) {
					Deque<Point> que = list.get(i);
					if (que.size()>0) {
						Point p=que.peek();
						if (set.contains(p)) {
							que.poll();
							Point p2=set.floor(p);
							Deque<Point> que2 = list.get(p2.ed);
							que2.poll();
							n--;
							flag[i]=false;
							flag[p2.ed]=false;
							bhit=true;
						} else {
							set.add(p);
							flag[i]=true;
						}
					}
				}
			}
			if (!bhit) {
				System.out.println("No");
				System.exit(0);
			}
		}
		System.out.println("Yes");
	}
	long ans = 0;//Integer.MAX_VALUE;
	int n;
	long ln;
	String s;
	StringBuilder sb = new StringBuilder();
	TreeMap<Integer,Integer> map = new TreeMap<>();
	int[] ary;
/*
2 2
2
1 2
2
1 2

2 2
2
1 2
2
2 1
*/

	static class Point implements Comparable<Point> {
		final int st;
		final int ed;
		Point(int st, int ed) {
			this.st = st;
			this.ed = ed;
		}
		@Override
		public int compareTo(Main.Point that) {
			int cmp = Integer.compare(this.st, that.st);
			return cmp;
//			if (cmp != 0) return cmp;
//			return Integer.compare(this.ed, that.ed);
		}
		@Override
		public int hashCode() {
			return Objects.hash(st, ed);
		}
		@Override
		public boolean equals(Object obj) {
			Point that = (Point) obj;
			return this.st == that.st;
//			return this.st == that.st && this.ed == that.ed;
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
