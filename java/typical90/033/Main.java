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
		int h = sc.nextInt();
		int w = sc.nextInt();
		if (h == 1 || w == 1) {
			System.out.println(h*w);
			System.exit(0);
		}
		int hh = h/2 + h%2;
		int ww = w/2 + w%2;
		long ans = hh*ww;
		System.out.println(ans);
	}
}
