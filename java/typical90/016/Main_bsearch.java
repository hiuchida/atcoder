import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	static Scanner sc;
	int n, a, b, c;
	void solve() {
		n = nextInt();
		a = nextInt();
		b = nextInt();
		c = nextInt();
		int ng = 0;
		int mx = 10*1000;
		while (ng+1<mx) {
			int mid = (ng+mx)/2;
			DEBUG("test " + ng + " " + mid + " " + mx);
			int rc = test(mid);
			if (rc <= mid) {
				mx = rc;
			} else {
				ng = mid;
			}
		}
		System.out.println(mx);
	}
	int test(int mx) {
		int ans = Integer.MAX_VALUE;
		long s1, s2, s3;
		for (int i=0; i<=mx; i++) {
			if (ans < i) break;
			s1 = (long)a*i;
			if (s1>n) break;
			for (int j=0; i+j<=mx; j++) {
				if (ans < i+j) break;
				s2 = (long)b*j;
				if (s1+s2>n) break;
				int k=(int)((n-s1-s2)/c);
				s3 = (long)c*k;
				if (s1+s2+s3==n) {
					ans = Math.min(ans, i+j+k);
					if (ans <= mx) {
						DEBUG(i + " " + j + " " + k + " " + ans + " " + mx);
						return ans;
					}
				}
			}
		}
		return Integer.MAX_VALUE;
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
