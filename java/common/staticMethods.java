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
		if (a < b) {
			if (a == 0) return b;
			else return gcd(a, b % a);
		}
		if (b == 0) return a;
		else return gcd(b, a % b);
	}
