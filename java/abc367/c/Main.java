import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static Scanner sc;
	long ans = 0;
	int n;
	int k;
	int[] ary;
	void solve() {
		n = nextInt();
		k = nextInt();
		ary = nextIntAry(n);
		DEBUG(ary);
		List<Integer> list = new ArrayList<>();
		dfs(0, list);
	}
	void dfs(int s, List<Integer> list) {
		int i=list.size();
		if (i==n) {
			if (s%k==0) {
				String ss = list.toString();
				ss = ss.replace("[", "").replace("]", "").replaceAll(",", "");
				System.out.println(ss);
			}
			return;
		}
		for (int j=1; j<=ary[i]; j++) {
			list.add(j);
			dfs(s+j, list);
			list.remove(list.size()-1);
		}
	}
/*
*/

	//---------------------------------------------------------------
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
