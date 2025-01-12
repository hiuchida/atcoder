import java.util.*;
public class Main {
	static final boolean DEBUG = true;
	void solve() {
		int xa = nextInt();
		int ya = nextInt();
		int xb = nextInt();
		int yb = nextInt();
		int xc = nextInt();
		int yc = nextInt();
		long ab = calc(xa, ya, xb, yb);
		long bc = calc(xb, yb, xc, yc);
		long ac = calc(xa, ya, xc, yc);
		if (ab < ac && bc < ac) {
			if (ac==ab+bc) ok();
			ng();
		} else if (bc < ab && ac < ab) {
			if (ab==bc+ac) ok();
			ng();
		} else {
			if (bc==ab+ac) ok();
			ng();
		}
	}
	long calc(int x1, int y1, int x2, int y2) {
		long x = (x1-x2);
		long y = (y1-y2);
		return x*x+y*y;
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
0 0
4 0
0 3

-4 3
2 1
3 4

2 4
-3 2
1 -2
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
}
