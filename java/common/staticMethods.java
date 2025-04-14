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
	//abc082_b,abc192_c: 指定された配列の要素の順序を逆にします。see Collections.reverse()
	static void reverse(char[] ary) {
		for (int i=0, mid=ary.length/2, j=ary.length-1; i<mid; i++, j--) {
			char tmp=ary[i];
			ary[i]=ary[j];
			ary[j]=tmp;
		}
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
	//abc181_b: 初項a、末項bの等差数列の和
	static long calc(int a, int b) {
		long n=b-a+1;
		long ans=n*(a+b)/2;
		return ans;
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
	//abc080_b,abc083_b,abc101_b,abc187_a: nを十進法で表したときの各桁の和
	static int calc(int n) {
		int ans=0;
		while (n>0) {
			ans+=n%10;
			n/=10;
		}
		return ans;
	}
	//abc220_b: k進表現のxを十進数に変換する
	static long calc(int k, long x) {
		long ans=0;
		long w=1;
		while (x>0) {
			long v=x%10;
			ans+=v*w;
			w*=k;
			x/=10;
		}
		return ans;
	}
	//abc220_b: k進表現のsを十進数に変換する
	static long calc(int k, String s) {
		long ans=0;
		for (int i=0; i<s.length(); i++) {
			long v=s.charAt(i)-'0';
			ans*=k;
			ans+=v;
		}
		return ans;
	}
	//abc285_c: A-Zのk進表現のsを十進数に変換する
	static long calc(int k, String s) {
		long ans=0;
		for (int i=0; i<s.length(); i++) {
			long v=s.charAt(i)-'A'+1;
			ans*=k;
			ans+=v;
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
	//abc192_b,abc281_b: 半角英大文字の判定
	static boolean checkUpper(char ch) {
		if ('A'<=ch && ch<='Z') return true;
		return false;
	}
	//abc192_b: 半角英小文字の判定
	static boolean checkLower(char ch) {
		if ('a'<=ch && ch<='z') return true;
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
	//abc180_b: x1,y1からx2,y2までのマンハッタン距離
	static long calc1(long x1, long y1, long x2, long y2) {
		long dx=x1-x2;
		long dy=y1-y2;
		return Math.abs(dx)+Math.abs(dy);
	}
	//abc180_b: x1,y1からx2,y2までのチェビシェフ距離
	static long calc(long x1, long y1, long x2, long y2) {
		long dx=x1-x2;
		long dy=y1-y2;
		return Math.max(Math.abs(dx), Math.abs(dy));
	}
