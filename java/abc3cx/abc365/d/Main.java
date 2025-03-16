import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	void solve() {
		n = nextInt();
		s = next();
		int[] dpr = new int[n+1];
		int[] dpp = new int[n+1];
		int[] dps = new int[n+1];
		for (int i=0; i<n; i++) {
			char ch = s.charAt(i);
			switch (ch) {
			case 'R':
				dpp[i+1] = Math.max(dpr[i], dps[i]) + 1;
				dpr[i+1] = Math.max(dpp[i], dps[i]);
				break;
			case 'P':
				dps[i+1] = Math.max(dpr[i], dpp[i]) + 1;
				dpp[i+1] = Math.max(dpr[i], dps[i]);
				break;
			case 'S':
				dpr[i+1] = Math.max(dpp[i], dps[i]) + 1;
				dps[i+1] = Math.max(dpr[i], dpp[i]);
				break;
			}
		}
		DEBUG(dpr);
		DEBUG(dpp);
		DEBUG(dps);
		ans = Math.max(Math.max(dpr[n], dpp[n]), dps[n]);
		System.out.println(ans);
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
