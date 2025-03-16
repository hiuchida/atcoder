import java.util.*;
public class Main {
	static final boolean DEBUG = true;
	TreeMap<Integer,TreeSet<Integer>> map = new TreeMap<>();
	boolean[] done;
	void add(int a, int b) {
		TreeSet<Integer> set = map.get(a);
		if (set==null) set=new TreeSet<>();
		set.add(b);
		map.put(a, set);
	}
	void solve() {
		n = nextInt();
		done = new boolean[n+1];
		for (int i=0; i<n-1; i++) {
			int a = nextInt();
			int b = nextInt();
			add(a,b);
			add(b,a);
		}
//		DEBUG(map);
		done[1]=true;
		dfs(1);
		System.out.println(sb);
	}
	void dfs(int i) {
		if (sb.length()>0) sb.append(" ");
		sb.append(i);
		TreeSet<Integer> set = map.get(i);
		for (int p : set) {
			if (!done[p]) {
				done[p]=true;
				dfs(p);
				if (sb.length()>0) sb.append(" ");
				sb.append(i);
			}
		}
	}
	long ans = 0;//Integer.MAX_VALUE;
	int n;
	long ln;
	String s;
	StringBuilder sb = new StringBuilder();
	List<String> list = new ArrayList<>();
	Deque<Integer> que = new ArrayDeque<>();
	int[] ary;
/*
4
1 2
4 2
3 1

5
1 2
1 3
1 4
1 5
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
