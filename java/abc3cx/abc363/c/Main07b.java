import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	void solve() {
		n = nextInt();
		flag=new boolean[n];
		k = nextInt();
		s = next();
		ary = s.toCharArray();
//		is("zzxyy");
		char[] buf=new char[n];
		dfs(buf, 0);
//		DEBUG(strs);
//		DEBUG(strs.size());
		int cnt=strs.size();
//		Collections.sort(strs);
//		int cnt=0;
//		String pre="";
//		for (int i=0; i<strs.size(); i++) {
//			String s=strs.get(i);
//			if (!pre.equals(s)) {
//				cnt++;
//				pre=s;
//			}
//		}
		System.out.println(cnt);
	}
	void dfs(char[] buf, int len) {
		if (len == n) {
			if (!is(buf)) strs.add(new String(buf));
			return;
		}
		for (int i=0; i<n; i++) {
			if (flag[i]) continue;
			flag[i]=true;
			buf[len]=ary[i];
			dfs(buf, len+1);
			flag[i]=false;
		}
	}
	boolean is(char[] buf) {
		for (int i=0; i<=n-k; i++) {
			boolean rc = true;
			for (int j=0; j<k; j++) {
				if (buf[i+j] == buf[i+k-1-j]) {
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
	long ans = 0;//Integer.MAX_VALUE;
	char[] ary;
	int n;
	int k;
	long ln;
	String s;
	StringBuilder sb = new StringBuilder();
	List<String> list = new ArrayList<>();
	Deque<Integer> que = new ArrayDeque<>();
	boolean[] flag;
	Set<String> strs = new HashSet<>(440640);
/*
3 2
aab

5 3
zzyyx

10 5
abcwxyzyxw
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
