import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
	final int _intMax = Integer.MAX_VALUE; // =2147483647>10^9
	final int _intMin = Integer.MIN_VALUE;
	final long _longMax = Long.MAX_VALUE; // =9223372036854775807L>10^18
	final long _longMin = Long.MIN_VALUE;
	final char[] _azAry = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
			's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	void solve() {
		String s = readLine();
		int k = readNum();
		Set<String> set = new HashSet<>();
		for (int i = 1; i <= s.length(); i++) {
			for (int j = 0; j <= s.length() - i; j++) {
				String sub = s.substring(j, j + i);
				set.add(sub);
			}
		}
		List<String> sl = new ArrayList<>(set);
		Collections.sort(sl);
		pln(sl.get(k - 1));
	}

	// -----------------------------------------------------
	// 2018/05/06 r31
	// -----------------------------------------------------
	List<Character> getazList() {
		List<Character> list = new ArrayList<>();
		for (char ch : _azAry)
			list.add(ch);
		return list;
	}

	int getDx4(int idx) {
		int[] dx = { 0, 1, 0, -1 };
		return dx[idx];
	}

	int getDy4(int idx) {
		int[] dy = { -1, 0, 1, 0 };
		return dy[idx];
	}

	int getDx8(int idx) {
		int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };
		return dx[idx];
	}

	int getDy8(int idx) {
		int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
		return dy[idx];
	}

	class Bitmap {
		int mx;
		int my;
		boolean[][] map;

		public Bitmap(int mx, int my) {
			this.mx = mx;
			this.my = my;
			map = new boolean[my + 2][mx + 2];
		}

		public void readLines(char ch) {
			for (int y = 1; y <= my; y++) {
				String s = readLine();
				for (int x = 1; x <= mx; x++) {
					if (s.charAt(x - 1) == ch)
						set(x, y);
				}
			}
		}

		public int count4(int x, int y) {
			int cnt = 0;
			for (int i = 0; i < 4; i++) {
				if (is(x + getDx4(i), y + getDy4(i)))
					cnt++;
			}
			return cnt;
		}

		public int count8(int x, int y) {
			int cnt = 0;
			for (int i = 0; i < 8; i++) {
				if (is(x + getDx8(i), y + getDy8(i)))
					cnt++;
			}
			return cnt;
		}

		public boolean is(int x, int y) {
			return map[y][x];
		}

		public void set(int x, int y) {
			map[y][x] = true;
		}

		public void set(int x, int y, boolean b) {
			map[y][x] = b;
		}

		public void reset(int x, int y) {
			map[y][x] = false;
		}
	}

	class CharList implements Iterable<Character> {
		class CharComparator implements Comparator<Character> {
			int sign;

			public CharComparator(boolean bAsc) {
				sign = bAsc ? 1 : -1;
			}

			public int compare(Character o1, Character o2) {
				return sign * Character.compare(o1, o2);
			}
		}

		List<Character> list = new ArrayList<>();
		CharComparator asc = new CharComparator(true);
		CharComparator desc = new CharComparator(false);

		public void add(char ch) {
			list.add(ch);
		}

		public char get(int idx) {
			return list.get(idx);
		}

		public char getLast() {
			return list.get(list.size() - 1);
		}

		public Iterator<Character> iterator() {
			return list.iterator();
		}

		public void remove(int idx) {
			list.remove(idx);
		}

		public void removeLast() {
			list.remove(list.size() - 1);
		}

		public int size() {
			return list.size();
		}

		public void sort(boolean bAsc) {
			if (bAsc)
				Collections.sort(list, asc);
			else
				Collections.sort(list, desc);
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (char ch : list)
				sb.append(ch);
			return sb.toString();
		}
	}

	class Counter<K> {
		Map<K, Integer> map = new HashMap<>();

		public Counter() {
		}

		public Counter(Iterable<K> itr) {
			for (K obj : itr) {
				add(obj);
			}
		}

		public void add(K key) {
			Integer cnt = map.get(key);
			if (cnt == null)
				map.put(key, 1);
			else
				map.put(key, cnt + 1);
		}

		public int get(K key) {
			Integer cnt = map.get(key);
			if (cnt == null)
				return 0;
			else
				return cnt;
		}

		public Set<K> keySet() {
			return map.keySet();
		}
	}

	class IntList implements Iterable<Integer> {
		class Info {
			int idx;
			int val;

			public Info(int idx, int val) {
				this.idx = idx;
				this.val = val;
			}

			public String toString() {
				return "(" + idx + ", " + val + ")";
			}
		}

		class InfoComparator implements Comparator<Info> {
			int sign;

			public InfoComparator(boolean bAsc) {
				sign = bAsc ? 1 : -1;
			}

			public int compare(Info o1, Info o2) {
				return sign * Integer.compare(o1.val, o2.val);
			}
		}

		List<Info> list = new ArrayList<>();
		InfoComparator asc = new InfoComparator(true);
		InfoComparator desc = new InfoComparator(false);

		public IntList() {
		}

		public IntList(int[] ia) {
			for (int i = 0; i < ia.length; i++)
				add(ia[i]);
		}

		public void add(int val) {
			list.add(new Info(list.size(), val));
		}

		public void add(int idx, int val) {
			list.add(new Info(idx, val));
		}

		public int getIdx(int idx) {
			return list.get(idx).idx;
		}

		public int getVal(int idx) {
			return list.get(idx).val;
		}

		public int getLastVal() {
			return list.get(list.size() - 1).val;
		}

		public Iterator<Integer> iterator() {
			List<Integer> vallist = new ArrayList<>();
			for (Info info : list)
				vallist.add(info.val);
			return vallist.iterator();
		}

		public void remove(int idx) {
			list.remove(idx);
		}

		public void removeLast() {
			list.remove(list.size() - 1);
		}

		public int size() {
			return list.size();
		}

		public void sort(boolean bAsc) {
			if (bAsc)
				Collections.sort(list, asc);
			else
				Collections.sort(list, desc);
		}
	}

	class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Point(Point pt) {
			this.x = pt.x;
			this.y = pt.y;
		}

		public boolean equals(Object o) {
			if (o instanceof Point) {
				Point that = (Point) o;
				return (x == that.x) && (y == that.y);
			}
			return false;
		}

		public long getManhattanDistance(Point pt) {
			return abs((long) pt.x - this.x) + abs((long) pt.y - this.y);
		}

		public int hashCode() {
			return x + (y * 31);
		}

		public String toString() {
			return "(" + x + ", " + y + ")";
		}
	}

	class PointList implements Iterable<Point> {
		class PointComparator implements Comparator<Point> {
			int mode;

			public PointComparator(int prop, boolean bAsc) {
				switch (prop) {
				case 1:
					if (bAsc)
						this.mode = 11;
					else
						this.mode = 12;
					break;
				case 2:
					if (bAsc)
						this.mode = 21;
					else
						this.mode = 22;
					break;
				default:
					throw new RuntimeException();
				}
			}

			public int compare(Point p1, Point p2) {
				switch (mode) {
				case 11:
					return 1 * Integer.compare(p1.x, p2.x);
				case 12:
					return -1 * Integer.compare(p1.x, p2.x);
				case 21:
					return 1 * Integer.compare(p1.y, p2.y);
				case 22:
					return -1 * Integer.compare(p1.y, p2.y);
				}
				throw new IllegalStateException();
			}
		}

		List<Point> list = new ArrayList<>();

		public void add(int x, int y) {
			list.add(new Point(x, y));
		}

		public Point get(int idx) {
			return list.get(idx);
		}

		public Iterator<Point> iterator() {
			return list.iterator();
		}

		public void remove(int idx) {
			list.remove(idx);
		}

		public int size() {
			return list.size();
		}

		public void sort(int prop, boolean bAsc) {
			PointComparator c = new PointComparator(prop, bAsc);
			Collections.sort(list, c);
		}
	}

	class Prime {
		final int maxPrime = 1000;
		final int[] prime = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83,
				89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193,
				197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313,
				317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443,
				449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587,
				593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719,
				727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859,
				863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997 };
		final Set<Integer> primeSet = new HashSet<>();

		Prime() {
			for (int p : prime)
				primeSet.add(p);
		}

		boolean isPrime(int n) {
			if (n <= maxPrime)
				return primeSet.contains(n);
			for (int i : prime) {
				if (n % i == 0)
					return false;
			}
			int sqrtNum = (int) Math.ceil(Math.sqrt(n));
			for (int i = maxPrime + 1; i <= sqrtNum; i += 2) {
				if (n % i == 0)
					return false;
			}
			return true;
		}
	}

	class StrList implements Iterable<String> {
		class StrComparator implements Comparator<String> {
			int sign;

			public StrComparator(boolean bAsc) {
				sign = bAsc ? 1 : -1;
			}

			public int compare(String o1, String o2) {
				return sign * o1.compareTo(o2);
			}
		}

		List<String> list = new ArrayList<>();
		StrComparator asc = new StrComparator(true);
		StrComparator desc = new StrComparator(false);

		public StrList() {
		}

		public StrList(String[] sa) {
			for (int i = 0; i < sa.length; i++)
				list.add(sa[i]);
		}

		public void add(String s) {
			list.add(s);
		}

		public String get(int idx) {
			return list.get(idx);
		}

		public Iterator<String> iterator() {
			return list.iterator();
		}

		public int size() {
			return list.size();
		}

		public void sort(boolean bAsc) {
			if (bAsc)
				Collections.sort(list, asc);
			else
				Collections.sort(list, desc);
		}
	}

	int abs(int a) {
		return (a >= 0) ? a : -a;
	}

	long abs(long a) {
		return (a >= 0) ? a : -a;
	}

	long ceil(long a, long b) {
		if (a < 0) {
			return -1 * floor(-a, b);
		}
		return ((a + b - 1) / b) * b;
	}

	long floor(long a, long b) {
		if (a < 0) {
			return -1 * ceil(-a, b);
		}
		return (a / b) * b;
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

	void sort(char[] ca) {
		Arrays.sort(ca);
	}

	void sort(int[] ia) {
		Arrays.sort(ia);
	}

	void sort(long[] la) {
		Arrays.sort(la);
	}

	void sort(String[] sa) {
		Arrays.sort(sa);
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

	String[] readLines(int n) {
		String[] lines = new String[n];
		for (int i = 0; i < n; i++)
			lines[i] = readLine();
		return lines;
	}

	CharList readChars() {
		CharList list = new CharList();
		String line = readLine();
		for (int i = 0; i < line.length(); i++)
			list.add(line.charAt(i));
		return list;
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
