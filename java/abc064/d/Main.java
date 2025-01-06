import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static Scanner sc;
	void solve() {
		int n = nextInt();
		String s = next();
		DEBUG(s);
		char[] ary = s.toCharArray();
		int kakko = 0;
		int adf = 0;
		for (int i=0; i<ary.length; i++) {
			if (ary[i] == '(') kakko++;
			else kakko--;
			if (kakko<0) {
				adf++;
				kakko++;
			}
		}
		StringBuilder sb = new StringBuilder();
		while (adf-->0) {
			sb.append('(');
		}
		sb.append(s);
		s = sb.toString();
		DEBUG(s);

		ary = s.toCharArray();
		kakko = 0;
		for (int i=0; i<ary.length; i++) {
			if (ary[i] == '(') kakko++;
			else kakko--;
		}
		sb = new StringBuilder();
		sb.append(s);
		while (kakko-->0) {
			sb.append(')');
		}
		s = sb.toString();
		DEBUG(s);

		System.out.println(s);
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
