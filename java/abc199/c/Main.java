import java.util.*;
public class Main {
	static final boolean DEBUG = true;
	static Scanner sc;
	long ans = 0;
	int n;
	char[] ary;
	boolean bChange = false;
	int idx(int i) {
		i--;
		if (bChange) {
			i = (i+n)%(2*n);
		}
		return i;
	}
	void swap(int a, int b) {
		a = idx(a);
		b = idx(b);
		char ch = ary[a];
		ary[a] = ary[b];
		ary[b] = ch;
	}
	void change() {
		bChange = !bChange;
	}
	String string() {
		if (bChange) {
			char[] ary2 = new char[ary.length];
			for (int i=0; i<n; i++) {
				ary2[i] = ary[i+n];
				ary2[i+n] = ary[i];
			}
			String s = new String(ary2);
			return s;
		}
		String s = new String(ary);
		return s;
	}
	void solve() {
		n = nextInt();
		String s = next();
		ary = s.toCharArray();
		int q = nextInt();
		for (int i=0; i<q; i++) {
			int t = nextInt();
			int a = nextInt();
			int b = nextInt();
			switch (t) {
			case 1:
				swap(a, b);
				break;
			case 2:
				change();
				break;
			}
		}
		s = string();
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
