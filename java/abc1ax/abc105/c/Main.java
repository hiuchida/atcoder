import java.util.*;
public class Main {
	static final boolean DEBUG = true;
	static Scanner sc;
	long ans = 0;
	void solve() {
		final long w=1000*1000*1000;
		long n = nextInt();
		if (n==0) {
			System.out.println(0);
			System.exit(0);
		}
		String s = "";
		boolean bPlus = true;
		while (n!=0) {
			long nn=n;
			if (nn<0) {
				nn+=w;
				while (nn<0) nn+=2;
			}
//			DEBUG(n + " " + nn);
			if (nn%2==1) {
				s="1"+s;
				if (bPlus) {
					if (n>0) n--;
					else if (n<0) n--;
				} else {
					if (n>0) n--;
					else if (n<0) n--;
				}
			} else {
				s="0"+s;
			}
			n/=2;
			n=-n;
			bPlus = !bPlus;
		}
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
