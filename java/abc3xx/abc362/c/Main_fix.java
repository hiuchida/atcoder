import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	void solve() {
		int n = nextInt();
		int[] al = new int[n];
		int[] ar = new int[n];
		long min=0, max=0;
		for (int i=0; i<n; i++) {
			al[i] = nextInt();
			ar[i] = nextInt();
			min+=al[i];
			max+=ar[i];
		}
		DEBUG(min);
		DEBUG(max);
		if (min<0&&max<0) ng();
		else if (min>0&&max>0) ng();
		System.out.println("Yes");
		long dif=min;
		DEBUG(dif);
		List<String> ans = new ArrayList<>();
		for (int i=0; i<n; i++) {
			if (dif<0) {
				long dd=Math.min(ar[i]-al[i], -dif);
				ans.add(""+(al[i]+dd));
				dif+=dd;
			} else {
				ans.add(""+al[i]);
			}
		}
		System.out.println(String.join(" ", ans));
	}
/*
3
3 5
-4 1
-2 3

3
1 2
1 2
1 2

6
-87 12
-60 -54
2 38
-76 6
87 96
-17 38
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
