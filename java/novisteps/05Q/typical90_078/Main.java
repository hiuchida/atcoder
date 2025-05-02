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
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] ary = new int[n+1];
		for (int i=0; i<m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			if (a < b) {
				ary[b]++;
			} else {
				ary[a]++;
			}
		}
		DEBUG(ary);
		long ans = 0;
		for (int i=1; i<=n; i++) {
			if (ary[i] == 1) ans++;
		}
		System.out.println(ans);
	}
}
