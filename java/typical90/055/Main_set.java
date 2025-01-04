import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static void DEBUG(Object x) {
		if (DEBUG) System.out.println(x);
	}
	static void DEBUG(long x) {
		if (DEBUG) DEBUG(""+x);
    }
	static void DEBUG(int[] x) {
		if (DEBUG) DEBUG(Arrays.toString(x));
    }
	static int n;
	static long p;
	static int q;
	static int[] ary;
	static long ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		p = sc.nextInt();
		q = sc.nextInt();
		ary = new int[n];
		for (int i=0; i<n; i++) {
			int a = sc.nextInt();
			ary[i] = a;
		}
		DEBUG(ary);
		TreeSet<Integer> set = new TreeSet<>(); //set<i>
		dfs(0, 1, set);
		System.out.println(ans);
	}
	static void dfs(int i, long s, TreeSet<Integer> set) {
		if (set.size() == 5) {
			DEBUG(set + " " + s + " " + s%p);
			if (s%p == q) ans++;
			return;
		}
		if (i >= n) return;
		long s1 = s * ary[i] % p;
		set.add(i);
		dfs(i+1, s1, set);
		set.remove(i);
		dfs(i+1, s, set);
	}
}
