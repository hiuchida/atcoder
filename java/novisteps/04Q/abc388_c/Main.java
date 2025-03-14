import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	void solve() {
		n = nextInt();
		ary = nextIntAry(n);
		Arrays.sort(ary);
		DEBUG(ary);
		for (int i=n-1;i>=0;i--) {
			ans+=count(i);
		}
		System.out.println(ans);
	}
	long count(int i) {
		int h=ary[i]/2;
		int ng=n;
		int ok=-1;
		while (ok+1<ng) {
			int mid=ok+(ng-ok)/2;
			if (ary[mid]<=h) ok=mid;
			else ng=mid;
		}
		DEBUG(ok+" "+ng);
		if (ok>=0) return ok+1;
		return 0;
	}
	long count0(int i) {
		int h=ary[i]/2;
		int idx = Arrays.binarySearch(ary, h);
		if (idx<0) {
			idx=(~idx)-1;
		}
		if (idx>=0) {
			DEBUG(idx+1);
			return idx+1;
		}
		return 0;
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
6
2 3 4 4 7 10

3
387 388 389

32
1 2 4 5 8 10 12 16 19 25 33 40 50 64 87 101 149 175 202 211 278 314 355 405 412 420 442 481 512 582 600 641
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
