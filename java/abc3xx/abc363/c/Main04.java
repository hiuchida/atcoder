import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	void solve() {
		n = nextInt();
		k = nextInt();
		s = next();
		ary = s.toCharArray();
//		is("zzxyy");
		dfs("");
		DEBUG(strs);
		DEBUG(strs.size());
		List<String> ans = new ArrayList<>();
		for (String s0 : strs) {
			if (!is(s0)) ans.add(s0);
		}
		Collections.sort(ans);
		int cnt=0;
		String pre="";
		for (int i=0; i<ans.size(); i++) {
			String s=ans.get(i);
			if (!pre.equals(s)) {
				cnt++;
				pre=s;
			}
		}
		System.out.println(cnt);
	}
	void dfs(String s0) {
		if (s0.length() == n) {
			strs.add(s0);
			return;
		}
		for (int i=0; i<n; i++) {
			if (set.contains(i)) continue;
			set.add(i);
			String s = s0 + String.valueOf(ary[i]);
			dfs(s);
			set.remove(i);
		}
	}
	boolean is(String s0) {
		for (int i=0; i<=n-k; i++) {
			boolean rc = true;
			for (int j=0; j<k; j++) {
				if (s0.charAt(i+j) == s0.charAt(i+k-1-j)) {
//					DEBUG(s0 + " " + (i+j) + " " + (i+k-1-j) + " T");
				}
				else {
//					DEBUG(s0 + " " + (i+j) + " " + (i+k-1-j) + " F");
					rc=false;
				}
			}
			if (rc) {
//				DEBUG(s0 + " " + i + " " + rc);
				return true;
			}
		}
		return false;
	}
	char[] ary;
	int n;
	int k;
	long ln;
	String s;
	StringBuilder sb = new StringBuilder();
	List<String> list = new ArrayList<>();
	Deque<Integer> que = new ArrayDeque<>();
	TreeSet<Integer> set = new TreeSet<>();
	List<String> strs = new ArrayList<>();
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
