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
		long n = sc.nextLong();
		int k = sc.nextInt();
		for (int i=0; i<k; i++) {
			long v = 0;
			long w = 1;
			while (n > 0) {
				int a=(int)(n%10);
				v+=a*w;
				w*=8;
				n/=10;
			}
			n = v;
			
			w = 1;
			v = 0;
			while (n > 0) {
				int a=(int)(n%9);
				if (a==8) a=5;
				v+=a*w;
				w*=10;
				n/=9;
			}
			n = v;
		}
		System.out.println(n);
	}
}
