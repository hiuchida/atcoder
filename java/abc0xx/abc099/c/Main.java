import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	void solve() {
		n = nextInt();
		int[] dp = new int[n+1];
		for (int i=0; i<=n; i++) {
			dp[i] = i;
		}
		for (int i=0; i<n; i++) {
			for (int j=6; i+j<=n; j*=6) {
				dp[i+j] = Math.min(dp[i+j], dp[i]+1);
			}
			for (int j=9; i+j<=n; j*=9) {
				dp[i+j] = Math.min(dp[i+j], dp[i]+1);
			}
		}
		DEBUG(dp);
		ans = dp[n];
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
