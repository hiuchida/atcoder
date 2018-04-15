package abc091.b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main {
	final int _intMax = Integer.MAX_VALUE; // =2147483647>10^9
	final int _intMin = Integer.MIN_VALUE;
	final long _longMax = Long.MAX_VALUE; // =9223372036854775807L>10^18
	final long _longMin = Long.MIN_VALUE;
	static boolean bElapsed = false;

	void solve() {
		Counter c1 = new Counter();
		Counter c2 = new Counter();
		int n = readNum();
		for (int i = 0; i < n; i++) {
			String line = readLine();
			c1.add(line);
		}
		int m = readNum();
		for (int i = 0; i < m; i++) {
			String line = readLine();
			c2.add(line);
		}
		int ans = 0;
		for (Object k : c1.keySet()) {
			int v1 = c1.get(k);
			int v2 = c2.get(k);
			ans = max(ans, v1 - v2);
		}
		pln(ans);
	}

	class Counter {
		Map<Object, Integer> map = new HashMap<>();

		void add(Object o) {
			Integer v = map.get(o);
			if (v == null) {
				map.put(o, 1);
			} else {
				map.put(o, v + 1);
			}
		}

		int get(Object o) {
			Integer v = map.get(o);
			if (v == null) {
				return 0;
			} else {
				return v;
			}
		}

		Set<Object> keySet() {
			return map.keySet();
		}
	}

	int max(int a, int b) {
		return (a > b) ? a : b;
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
