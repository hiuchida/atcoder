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
		final int N=3;
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			int[] ia = readNums();
			for (int j = 0; j < N; j++) {
				map[i][j] = ia[j];
			}
		}
		int d1 = map[0][1] - map[0][0];
		int d2 = map[1][1] - map[1][0];
		int d3 = map[2][1] - map[2][0];
		if (d1 != d2 || d2 != d3) {
			pln("No");
			return;
		}
		d1 = map[0][2] - map[0][1];
		d2 = map[1][2] - map[1][1];
		d3 = map[2][2] - map[2][1];
		if (d1 != d2 || d2 != d3) {
			pln("No");
			return;
		}
		d1 = map[1][0] - map[0][0];
		d2 = map[1][1] - map[0][1];
		d3 = map[1][2] - map[0][2];
		if (d1 != d2 || d2 != d3) {
			pln("No");
			return;
		}
		d1 = map[2][0] - map[1][0];
		d2 = map[2][1] - map[1][1];
		d3 = map[2][2] - map[1][2];
		if (d1 != d2 || d2 != d3) {
			pln("No");
			return;
		}
		pln("Yes");
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

	int[] readNums() {
		String[] flds = readFlds();
		int[] nums = new int[flds.length];
		for (int i = 0; i < flds.length; i++) {
			nums[i] = pint(flds[i]);
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
