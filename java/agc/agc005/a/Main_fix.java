import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
	final int _intMax = Integer.MAX_VALUE; // =2147483647>10^9
	final int _intMin = Integer.MIN_VALUE;
	final long _longMax = Long.MAX_VALUE; // =9223372036854775807L>10^18
	final long _longMin = Long.MIN_VALUE;

	void solve() {
		List<Character> list = new ArrayList<>();
		String s = readLine();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			switch (ch) {
			case 'S':
				list.add(ch);
				break;
			case 'T':
				if (list.size() > 0 && list.get(list.size() - 1) == 'S') {
					list.remove(list.size() - 1);
				} else {
					list.add(ch);
				}
				break;
			}
		}
		pln(list.size());
	}

	// -----------------------------------------------------
	// 2018/04/28 r9
	// -----------------------------------------------------
	int abs(int a) {
		return (a >= 0) ? a : -a;
	}

	long abs(long a) {
		return (a >= 0) ? a : -a;
	}

	int max(int a, int b) {
		return (a > b) ? a : b;
	}

	long max(long a, long b) {
		return (a > b) ? a : b;
	}

	int min(int a, int b) {
		return (a < b) ? a : b;
	}

	long min(long a, long b) {
		return (a < b) ? a : b;
	}

	int reed(long a, int n) {
		while (n-- > 0)
			a /= 10;
		return (int) (a % 10);
	}

	int sqrt(long a) {
		return (int) Math.sqrt(a);
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

	long readLong() {
		String line = readLine();
		return plong(line);
	}

	String[] readFlds() {
		String line = readLine();
		return line.split(" ");
	}

	int[] readNums() {
		String[] flds = readFlds();
		int[] nums = new int[flds.length];
		for (int i = 0; i < flds.length; i++)
			nums[i] = pint(flds[i]);
		return nums;
	}

	int[] readNums(int n) {
		int[] nums = new int[n];
		for (int i = 0; i < n; i++)
			nums[i] = readNum();
		return nums;
	}

	long[] readLongs() {
		String[] flds = readFlds();
		long[] nums = new long[flds.length];
		for (int i = 0; i < flds.length; i++)
			nums[i] = plong(flds[i]);
		return nums;
	}

	long[] readLongs(int n) {
		long[] nums = new long[n];
		for (int i = 0; i < n; i++)
			nums[i] = readLong();
		return nums;
	}

	Main pln() {
		_out.println();
		return this;
	}

	Main p(char c) {
		_out.print(c);
		return this;
	}

	Main p(char c, int n) {
		for (int i = 0; i < n; i++)
			p(c);
		return this;
	}

	Main pln(char c) {
		_out.println(c);
		return this;
	}

	Main p(double d) {
		_out.print(d);
		return this;
	}

	Main pln(double d) {
		_out.println(d);
		return this;
	}

	Main p(long l) {
		_out.print(l);
		return this;
	}

	Main pln(long l) {
		_out.println(l);
		return this;
	}

	Main p(String s) {
		_out.print(s);
		return this;
	}

	Main p(String s, int idx) {
		_out.print(s.charAt(idx));
		return this;
	}

	Main pln(String s) {
		_out.println(s);
		return this;
	}

	Main pln(int[] ia) {
		for (int i = 0; i < ia.length; i++)
			_out.println(ia[i]);
		return this;
	}

	Main pln(long[] la) {
		for (int i = 0; i < la.length; i++)
			_out.println(la[i]);
		return this;
	}

	static BufferedReader _in;
	static PrintWriter _out;
	static boolean _bElapsed = false;

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		_in = new BufferedReader(new InputStreamReader(System.in));
		_out = new PrintWriter(System.out);
		new Main().solve();
		_out.flush();
		long end = System.currentTimeMillis();
		if (_bElapsed)
			System.err.println((end - start) + "ms");
	}
}
