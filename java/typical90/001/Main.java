import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	void solve() {
		n = nextInt();
		l = nextInt();
		k = nextInt();
		ary = nextIntAry(n);
		DEBUG(ary);
		int ng=l+1;
		int ok=-1;
		while (ok+1<ng) {
			int mid=ng+(ok-ng)/2;
			boolean rc=check(mid);
			DEBUG(ng + " "+ mid + " " + ok + " " + rc);
			if (rc) ok=mid;
			else ng=mid;
		}
		ans = ok;
		System.out.println(ans);
	}
	boolean check(int x) {
		int pre = 0;
		int c = 0;
		for (int i=0; i<n; i++) {
			if (ary[i]-pre >= x) {
				pre=ary[i];
				c++;
			}
		}
		if (l-pre >= x) {
			c++;
		}
		if (c >= k+1) return true;
		return false;
	}
	long ans = 0;//Integer.MAX_VALUE;
	int n;
	int l;
	int k;
	long ln;
	String s;
	StringBuilder sb = new StringBuilder();
	List<String> list = new ArrayList<>();
	Deque<Integer> que = new ArrayDeque<>();
	TreeSet<Integer> set = new TreeSet<>();
	TreeMap<Integer,Integer> map = new TreeMap<>();
	int[] ary;
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
}
