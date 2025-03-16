import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	int[] dp;
	void solve() {
		n = nextInt();
		dp = new int[n+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		stack.addLast(n);
		while (stack.size() > 0) {
			int i = stack.removeLast();
			if (check(i)) {
				continue;
			}
			stack.addLast(i);
			int c = dp[i];
			boolean bNone = false;
			if (check(i-1)) {
				c = Math.min(c, dp[i-1]+1);
			} else {
				bNone = true;
				stack.addLast(i-1);
			}
			for (int j=6; i-j>=0; j*=6) {
				if (check(i-j)) {
					c = Math.min(c, dp[i-j]+1);
				} else {
					bNone = true;
					stack.addLast(i-j);
				}
			}
			for (int j=9; i-j>=0; j*=9) {
				if (check(i-j)) {
					c = Math.min(c, dp[i-j]+1);
				} else {
					bNone = true;
					stack.addLast(i-j);
				}
			}
			if (!bNone) {
				dp[i] = c;
				DEBUG(i + " " + c);
			}
		}
		ans = dp[n];
		DEBUG(dp);
		System.out.println(ans);
	}
	boolean check(int i) {
		if (dp[i] != Integer.MAX_VALUE) return true;
		return false;
	}
	long ans = 0;//Integer.MAX_VALUE;
	int n;
	long ln;
	String s;
	StringBuilder sb = new StringBuilder();
	List<String> list = new ArrayList<>();
	Deque<Integer> stack = new ArrayDeque<>();
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
