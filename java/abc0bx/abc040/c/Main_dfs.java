import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	int[] dp;
	void solve() {
		n = nextInt();
		ary = nextIntAry(n);
		DEBUG(ary);
		dp = new int[n];
		Arrays.fill(dp, Integer.MIN_VALUE);
		System.out.println(dfs(n-1));
	}
	int dfs(int i) {
		if (i==0) return 0;
		if (dp[i] != Integer.MIN_VALUE) return dp[i];
		if (i==1) {
			int c = Math.abs(ary[1]-ary[0]);
			dp[i] = c;
			return c;
		}
		int c1 = Math.abs(ary[i]-ary[i-1]);
		int c2 = Math.abs(ary[i]-ary[i-2]);
		int x1 = dfs(i-1);
		int x2 = dfs(i-2);
		int c = Math.min(x1+c1, x2+c2);
		dp[i] = c;
		return c;
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
	static void ok() {
		System.out.println("Yes");
		System.exit(0);
	}
	static void ng() {
		System.out.println("No");
		System.exit(0);
	}
}
