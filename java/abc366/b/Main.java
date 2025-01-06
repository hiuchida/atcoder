import java.util.*;
public class Main {
	static final boolean DEBUG = true;
	void solve() {
		n = nextInt();
		int len = 0;
		String[] sa = new String[n];
		for (int i=0; i<n; i++) {
			s = next();
			sa[i] = s;
			len = Math.max(len, s.length());
		}
//		DEBUG(len);
		char[][] ary = new char[len][n];
		for (int y=0; y<len; y++) {
			for (int x=0; x<n; x++) {
				String s = sa[n-x-1];
				if (y < s.length()) ary[y][x] = s.charAt(y);
				else ary[y][x] = '*';
			}
		}
		for (int y=0; y<len; y++) {
			for (int x=n-1; x>=0; x--) {
				if (ary[y][x] == '*') ary[y][x] = 0;
				else break;;
			}
		}
		for (int y=0; y<len; y++) {
			for (int x=0; x<n; x++) {
				if (ary[y][x] > 0) System.out.print(ary[y][x]);
			}
			System.out.println();
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
