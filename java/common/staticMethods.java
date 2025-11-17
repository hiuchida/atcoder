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
    static String getCRLF() {
    	return System.getProperty("line.separator");
    }
	static void ok() {
		System.out.println("Yes");
		System.exit(0);
	}
	static void ng() {
		System.out.println("No");
		System.exit(0);
	}
	//abc077_c: ary[mid]<x条件のバイナリーサーチ
	static int binarySearch(int[] ary, int x) {
		int lt=0;
		int rt=ary.length-1;
		while (lt<=rt) {
			int mid=(lt+rt)/2;
			if (ary[mid]<x) lt=mid+1;
			else rt=mid-1;
		}
		return lt;
	}
	//ソートされた配列int[] aryに対して、ary[i]>=xとなる最小のiを求める関数
	static int lowerBound(int[] ary, int x) {
		int lt=-1;
		int rt=ary.length;
		while (lt+1<rt) {
			int mid=(lt+rt)/2;
			if (ary[mid]>=x) rt=mid;
			else lt=mid;
		}
		return rt;
	}
	//abc082_b,abc192_c: 指定された配列の要素の順序を逆にします。see Collections.reverse()
	static void reverse(char[] ary) {
		for (int i=0, mid=ary.length/2, j=ary.length-1; i<mid; i++, j--) {
			char tmp=ary[i];
			ary[i]=ary[j];
			ary[j]=tmp;
		}
	}
	//ソート済のary[i]の重複を省く
	static int unique(int[] ary) {
		if (ary.length==0) return 0;
		int idx=1;
		for (int i=1; i<ary.length; i++) {
			if (ary[i-1]!=ary[i]) ary[idx++]=ary[i];
		}
		for (int i=idx; i<ary.length; i++) ary[i]=0;
		return idx;
	}
	//abc286_a,abc289_b,abc350_c: ary[i]とary[j]のスワップ
	static void swap(int[] ary, int i, int j) {
		int t=ary[i];
		ary[i]=ary[j];
		ary[j]=t;
	}
	//abc293_a: ary[i]とary[j]のスワップ
	static void swap(char[] ary, int i, int j) {
		char t=ary[i];
		ary[i]=ary[j];
		ary[j]=t;
	}
	//abc286_c: stから始まるbufの回文チェック
	static int check(char[] buf, int st) {
		int n=buf.length;
		int ans=0;
		for (int i=0; i<n/2; i++) {
			int i1=(i+st)%n;
			int i2=(n-1-i+st)%n;
			if (buf[i1]!=buf[i2]) {
				ans++;
			}
		}
		return ans;
	}
	//abc270_c,abc293_b,abc367_c,abc368_a,abc373_d,abc388_d: List等のtoString()の文字列からカッコとカンマを省く
	static String conv(String s) {
		return s.replace("[", "").replace("]", "").replaceAll(",", "");
	}
	static String join(String delimiter, int[] ary) {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<ary.length; i++) {
			if (i>0) sb.append(delimiter);
			sb.append(ary[i]);
		}
		return sb.toString();
	}
	//typical90_018
	//double値を指数表示(1.23E-4等)を抑制した文字列
	static String doubleToString(double v) {
		return BigDecimal.valueOf(v).toPlainString();
	}
	//abc032_a,abc070_c,abc109_c,abc118_c,abc125_c,abc148_c,abc253_d,abc276_d,abc284_d,typical90_022,typical90_038:
	//aとbの最大公約数
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
	//abc181_b,abc369_c,arc107_a: 初項a、末項bの等差数列の和
	static long calc(int a, int b) {
		long n=b-a+1;
		long ans=n*(a+b)/2;
		return ans;
	}
	//abc159_a,abc295_d,abc350_d,abc355_d,typical90_084: aCbの組み合わせ数
	static long calc(int a, int b) {
		long ans=1;
		for (int i=0; i<b; i++) ans*=a-i;
		for (int i=1; i<=b; i++) ans/=i;
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
	//abc020_b,abc353_d: x<10^kを満たす最小の10^k
	static long calc(int x) {
		long v=1;
		while (v<=x) v*=10;
		return v;
	}
	//abc357_d: x<10^kを満たす最小の10^k
	static long calc(long x) {
		long v=1;
		while (v<=x) v*=10;
		return v;
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
	//abc298_b: 時計周りに90度回転する
	static int[][] rotate(int n, int[][] ary) {
		int[][] ans=new int[n][n];
		for (int y=0; y<n; y++) {
			for (int x=0; x<n; x++) {
				ans[y][x]=ary[n-1-x][y];
			}
		}
		return ans;
	}
	//abc404_b: 時計周りに90度回転する
	static String[] rotate(String[] as) {
		char[][] ary=new char[n][n];
		for (int y=0; y<n; y++) {
			for (int x=0; x<n; x++) {
				ary[y][x]=as[n-1-x].charAt(y);
			}
		}
		String[] ans=new String[n];
		for (int y=0; y<n; y++) {
			ans[y]=new String(ary[y]);
		}
		return ans;
	}
	//abc298_b: aa[i][j]が1の場合のみab[i][j]と比較する
	static boolean comp(int n, int[][] aa, int[][] ab) {
		for (int y=0; y<n; y++) {
			for (int x=0; x<n; x++) {
				if (aa[y][x]==0) continue;
				if (aa[y][x]!=ab[y][x]) return false;
			}
		}
		return true;
	}
	//abc281_b: 半角数字の判定
	static boolean checkDigit(char ch) {
		if ('0'<=ch && ch<='9') return true;
		return false;
	}
	//abc192_b,abc281_b,abc291_a,abc402_a: 半角英大文字の判定
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
	//abc035_b,abc057_b,abc086_c,abc295_b: x1,y1からx2,y2までのマンハッタン距離
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
