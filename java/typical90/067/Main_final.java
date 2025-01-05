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
		String s = sc.next();
		int k = sc.nextInt();
		for (int i=0; i<k; i++) {
			long n = 0;
			long w = 1;
			for (int j=s.length()-1; j>=0; j--) {
				char ch = s.charAt(j);
				int a=(int)(ch-'0');
				n+=a*w;
				w*=8;
			}

			s = "";
			while (n > 0) {
				int a=(int)(n%9);
				if (a==8) a=5;
				s=a+s;
				n/=9;
			}
			if (s.length() == 0) s="0";
		}
		System.out.println(s);
	}
}
