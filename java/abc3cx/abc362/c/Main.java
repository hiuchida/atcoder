import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	void solve() {
		n = nextInt();
		int[] al = new int[n];
		int[] ar = new int[n];
		List<Point> list = new ArrayList<>();
		long min=0, max=0;
		long mmin=0, pmin=0;
		for (int i=0; i<n; i++) {
			al[i] = nextInt();
			ar[i] = nextInt();
			list.add(new Point(al[i], ar[i]));
			min+=al[i];
			max+=ar[i];
			if (al[i]>0 && ar[i]>0) pmin+=al[i];
			else if (al[i]<0 && ar[i]<0) mmin+=ar[i];
		}
		Collections.sort(list);
		DEBUG(list);
		DEBUG(min);
		DEBUG(max);
		DEBUG(mmin);
		DEBUG(pmin);
		if (min<0&&max<0) ng();
		else if (min>0&&max>0) ng();
		System.out.println("Yes");
		long dif=mmin+pmin;
		DEBUG(dif);
		List<String> ans = new ArrayList<>();
		for (int i=0; i<n; i++) {
			if (dif>0) {
				if (al[i]>0 && ar[i]>0) ans.add(""+al[i]);
				else if (al[i]<0 && ar[i]<0) {
					long dd=Math.min(ar[i]-al[i], dif);
					ans.add(""+(ar[i]-dd));
					dif-=dd;
				}
				else {
					long dd=Math.min(-al[i], dif);
					ans.add(""+(-dd));
					dif-=dd;
				}
			} else if (dif<0) {
				if (al[i]>0 && ar[i]>0) {
					long dd=Math.min(ar[i]-al[i], -dif);
					ans.add(""+(al[i]+dd));
					dif+=dd;
				}
				else if (al[i]<0 && ar[i]<0) ans.add(""+ar[i]);
				else {
					long dd=Math.min(ar[i], -dif);
					ans.add(""+(dd));
					dif+=dd;
				}
			} else {
				if (al[i]>0 && ar[i]>0) ans.add(""+al[i]);
				else if (al[i]<0 && ar[i]<0) ans.add(""+ar[i]);
				else ans.add("0");
			}
		}
		System.out.println(String.join(" ", ans));
	}
	long ans = 0;//Integer.MAX_VALUE;
	int n;
	long ln;
	String s;
	StringBuilder sb = new StringBuilder();
	Deque<Integer> que = new ArrayDeque<>();
	TreeSet<Integer> set = new TreeSet<>();
	TreeMap<Integer,Integer> map = new TreeMap<>();
	int[] ary;
/*
3
3 5
-4 1
-2 3

3
1 2
1 2
1 2

6
-87 12
-60 -54
2 38
-76 6
87 96
-17 38
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
