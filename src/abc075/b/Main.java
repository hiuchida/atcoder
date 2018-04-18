package abc075.b;

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

	boolean[][] map;

	void solve() {
		int[] ia = readNums();
		int h = ia[0];
		int w = ia[1];
		map = new boolean[h + 2][w + 2];
		for (int i = 1; i <= h; i++) {
			String line = readLine();
			if (line == null)
				break;
			for (int j = 1; j <= w; j++) {
				if (line.charAt(j - 1) == '#') {
					map[i][j] = true;
				}
			}
		}
		for (int i = 1; i <= h; i++) {
			for (int j = 1; j <= w; j++) {
				if (map[i][j])
					p("#");
				else
					p(count9(i, j));
			}
			pln("");
		}
	}

	int count9(int y, int x) {
		int ans = 0;
		for (int i = 0; i < 3; i++) {
			ans += count(y - 1, x - 1 + i);
			ans += count(y + 1, x - 1 + i);
		}
		ans += count(y, x - 1);
		ans += count(y, x + 1);
		return ans;
	}

	int count(int y, int x) {
		if (map[y][x])
			return 1;
		return 0;
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
