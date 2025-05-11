import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {
	final int _intMax = Integer.MAX_VALUE; // =2147483647>10^9
	final int _intMin = Integer.MIN_VALUE;
	final long _longMax = Long.MAX_VALUE; // =9223372036854775807L>10^18
	final long _longMin = Long.MIN_VALUE;
	static boolean bElapsed = false;

	void solve() {
		long[] la = readLNums();
		int n = (int) la[0];
		long c = la[1];
		long[] ix = new long[n];
		long[] iv = new long[n];
		for (int i = 0; i < n; i++) {
			long[] lb = readLNums();
			ix[i] = lb[0];
			iv[i] = lb[1];
		}
		long[] rans = new long[n];
		for (int i = 0; i < n; i++) {
			long px = (i == 0) ? 0 : ix[i - 1];
			rans[i] = iv[i] - (ix[i] - px);
		}
		long[] lans = new long[n];
		for (int i = 0; i < n; i++) {
			long px = (i == 0) ? c : ix[n - i];
			lans[i] = iv[n - i - 1] - (px - ix[n - i - 1]);
		}
		long mans = 0;
		for (int i = 0; i < n; i++) {
			long ans = 0;
			for (int j = 0; j <= i; j++) {
				ans += rans[j];
				if (mans < ans) {
					mans = ans;
				}
			}
			ans -= ix[i];
			for (int j = 0; j < n - i - 1; j++) {
				ans += lans[j];
				if (mans < ans) {
					mans = ans;
				}
			}
		}
		for (int i = 0; i < n; i++) {
			long ans = 0;
			for (int j = 0; j <= i; j++) {
				ans += lans[j];
				if (mans < ans) {
					mans = ans;
				}
			}
			ans -= c - ix[n - i - 1];
			for (int j = 0; j < n - i - 1; j++) {
				ans += rans[j];
				if (mans < ans) {
					mans = ans;
				}
			}
		}
		pln(mans);
	}

	int abs(int a) {
		return (a >= 0) ? a : -a;
	}

	int max(int a, int b) {
		return (a > b) ? a : b;
	}

	int min(int a, int b) {
		return (a < b) ? a : b;
	}

	int pint(String s) {
		return Integer.parseInt(s);
	}

	long plong(String s) {
		return Long.parseLong(s);
	}

	String readLine() {
		try {
			return _in.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	int readNum() {
		String line = readLine();
		return pint(line);
	}

	String[] readFlds() {
		String line = readLine();
		return line.split(" ");
	}

	long[] readLNums() {
		String[] flds = readFlds();
		long[] nums = new long[flds.length];
		for (int i = 0; i < flds.length; i++) {
			nums[i] = plong(flds[i]);
		}
		return nums;
	}

	void p(char c) {
		_out.print(c);
	}

	void pln(char c) {
		_out.println(c);
	}

	void p(double d) {
		_out.print(d);
	}

	void pln(double d) {
		_out.println(d);
	}

	void p(long l) {
		_out.print(l);
	}

	void pln(long l) {
		_out.println(l);
	}

	void p(String s) {
		_out.print(s);
	}

	void pln(String s) {
		_out.println(s);
	}

	static BufferedReader _in;
	static PrintWriter _out;

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		_in = new BufferedReader(new InputStreamReader(System.in));
		_out = new PrintWriter(System.out);
		new Main().solve();
		_out.flush();
		long end = System.currentTimeMillis();
		if (bElapsed) {
			System.err.println((end - start) + "ms");
		}
	}
}
