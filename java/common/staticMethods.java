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
	static final long M=998244353;
	static long mod(long val) {
		return val%M;
	}
	static long modadd(long val, long x) {
		return mod(val+x);
	}
	static void ok() {
		System.out.println("Yes");
		System.exit(0);
	}
	static void ng() {
		System.out.println("No");
		System.exit(0);
	}
	static String join(String delimiter, int[] ary) {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<ary.length; i++) {
			if (i>0) sb.append(delimiter);
			sb.append(ary[i]);
		}
		return sb.toString();
	}
	static long gcd(long a, long b) {
		if (b<a) {
			long t=a;
			a=b;
			b=t;
		}
		while (a>0) {
			long t=b%a;
			b=a;
			a=t;
		}
		return b;
	}
	static double calc(Point p1, Point p2) {
		double dx=p1.x-p2.x;
		double dy=p1.y-p2.y;
		return Math.sqrt(dx*dx+dy*dy);
	}
	//abc081_b: nを2で割り切れる回数
	static int calc(int n) {
		int ans=0;
		while (n%2==0) {
			ans++;
			n/=2;
		}
		return ans;
	}
	//abc083_b,abc101_b: n を十進法で表したときの各桁の和
	static int calc(int n) {
		int ans=0;
		while (n>0) {
			ans+=n%10;
			n/=10;
		}
		return ans;
	}
