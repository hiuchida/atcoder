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
	//abc065_c: valをMで割った余り
	static long mod(long val) {
		return val%M;
	}
	static long modadd(long val, long x) {
		return mod(val+x);
	}
	//abc065_c: val*xをMで割った余り
	static long modmul(long val, long x) {
		return mod(val*x);
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
	//abc048_b: a以上の最小のbの倍数
	static long ceil(long a, long b) {
		if (a < 0) {
			return -1 * floor(-a, b);
		}
		return ((a + b - 1) / b) * b;
	}
	//abc048_b: a以下の最大のbの倍数
	static long floor(long a, long b) {
		if (a < 0) {
			return -1 * ceil(-a, b);
		}
		return (a / b) * b;
	}
	//abc057_c: nの桁数
	static int calc(long n) {
		int ans=0;
		while (n>0) {
			ans++;
			n/=10;
		}
		return ans;
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
	//abc080_b,abc083_b,abc101_b: n を十進法で表したときの各桁の和
	static int calc(int n) {
		int ans=0;
		while (n>0) {
			ans+=n%10;
			n/=10;
		}
		return ans;
	}
	//abc279_c: ary[h][w]を縦横変換して、ans[w][h]を返す
	static String[] convert(int h, int w, String[] ary) {
		String[] ans=new String[w];
		for (int x=0; x<w; x++) {
			StringBuilder sb=new StringBuilder();
			for (int y=0; y<h; y++) {
				sb.append(ary[y].charAt(x));
			}
			ans[x]=sb.toString();
		}
		return ans;
	}
	//abc281_b: 半角数字の判定
	static boolean checkDigit(char ch) {
		if ('0'<=ch && ch<='9') return true;
		return false;
	}
	//abc281_b: 半角英大文字の判定
	static boolean checkUpper(char ch) {
		if ('A'<=ch && ch<='Z') return true;
		return false;
	}
	//abc234_b,abc255_b,abc374_d: p1からp2までのユークリッド距離
	static double calc(Point p1, Point p2) {
		long dx=p1.x-p2.x;
		long dy=p1.y-p2.y;
		return Math.sqrt(dx*dx+dy*dy);
	}
	//abc275_c,abc348_b: p1からp2までのユークリッド距離の2乗
	static long calc(Point p1, Point p2) {
		long dx=p1.x-p2.x;
		long dy=p1.y-p2.y;
		return dx*dx+dy*dy;
	}
	//abc362_b: x1,y1からx2,y2までのユークリッド距離の2乗
	static long calc(int x1, int y1, int x2, int y2) {
		long dx=x1-x2;
		long dy=y1-y2;
		return dx*dx+dy*dy;
	}
	//abc375_b: x1,y1からx2,y2までのユークリッド距離
	static double calc(long x1, long y1, long x2, long y2) {
		long dx=x1-x2;
		long dy=y1-y2;
		return Math.sqrt(dx*dx+dy*dy);
	}
	//abc057_b: x1,y1からx2,y2までのマンハッタン距離
	static int calc(int x1, int y1, int x2, int y2) {
		int dx=x1-x2;
		int dy=y1-y2;
		return Math.abs(dx)+Math.abs(dy);
	}
