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
		int k = sc.nextInt();
		int[] aa = new int[n];
		for (int i=0; i<n; i++) {
			int a = sc.nextInt();
			aa[i] = a;
		}
		int[] ab = new int[n];
		for (int i=0; i<n; i++) {
			int b = sc.nextInt();
			ab[i] = b;
		}
		int[] ad = new int[n];
		for (int i=0; i<n; i++) {
			ad[i] = ab[i]-aa[i];
		}
		DEBUG(aa);
		DEBUG(ab);
		DEBUG(ad);
		long ans = 0;
		for (int i=0; i<n; i++) {
			ans += Math.abs(ad[i]);
		}
		DEBUG(ans);
		if (ans > k) ng();
		k -= ans;
		if (k%2==0) ok();
		ng();
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
