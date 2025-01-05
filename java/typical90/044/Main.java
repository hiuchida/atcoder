import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static Scanner sc;
	long ans = 0;
	int n;
	int[] ary;
	int head = n-1;
	int idx(int x) {
		x += head;
		x %= n;
		return x;
	}
	void swap(int x, int y) {
		x = idx(x);
		y = idx(y);
		int tmp = ary[x];
		ary[x] = ary[y];
		ary[y] = tmp;
	}
	void shift() {
		head--;
		if (head < 0) head += n;
	}
	int get(int x) {
		x = idx(x);
		int v = ary[x];
		return v;
	}
	void solve() {
		n = nextInt();
		int q = nextInt();
		ary = nextIntAry(n);
		DEBUG(ary);
		for (int i=0; i<q; i++) {
			int t = nextInt();
			int x = nextInt();
			int y = nextInt();
			switch (t) {
			case 1:
				swap(x, y);
				break;
			case 2:
				shift();
				break;
			case 3:
				int v = get(x);
				System.out.println(v);
				break;
			}
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
