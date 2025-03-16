import java.util.*;
public class Main {
	static final boolean DEBUG = true;
	TreeMap<Long,Integer> map = new TreeMap<>();
	void add(long v) {
		Integer i=map.get(v);
		if (i==null) i=0;
		i++;
		map.put(v, i);
	}
	long get() {
		long v = map.firstKey();
		int i=map.get(v);
		if (i>1) {
			i--;
			map.put(v, i);
		} else {
			map.remove(v);
		}
		return v;
	}
	void solve() {
		int q = nextInt();
		long base=0;
		for (int i=0; i<q; i++) {
			int c = nextInt();
			long v;
			switch (c) {
			case 1:
				v = nextInt();
				v-=base;
				add(v);
				break;
			case 2:
				v = nextInt();
				base+=v;
				break;
			case 3:
				v = get();
				ans=v+base;
				System.out.println(ans);
				break;
			}
		}
	}
	long ans = 0;//Integer.MAX_VALUE;
	int n;
	long ln;
	String s;
	StringBuilder sb = new StringBuilder();
	List<String> list = new ArrayList<>();
	Deque<Integer> que = new ArrayDeque<>();
	int[] ary;
/*
3 1234 abc
4 7 3
5 6 2
*/

	//---------------------------------------------------------------
	static Scanner sc;
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		new Main().solve();
	}
	static int nextInt() {
		return sc.nextInt();
	}
	static long nextLong() {
		return sc.nextLong();
	}
	static String next() {
		return sc.next();
	}
	static int[] nextIntAry(int n) {
		int[] ary = new int[n];
		for (int i=0; i<n; i++) {
			int a = nextInt();
			ary[i] = a;
		}
		return ary;
	}
	static TreeSet<Integer> nextIntSet(int n) {
		TreeSet<Integer> set = new TreeSet<>();
		for (int i=0; i<n; i++) {
			int a = nextInt();
			set.add(a);
		}
		return set;
	}
	static void DEBUG(Object x) {
		if (DEBUG) System.out.println(x);
	}
	static void DEBUG(long x) {
		if (DEBUG) DEBUG(""+x);
    }
	static void DEBUG(int[] x) {
		if (DEBUG) DEBUG(Arrays.toString(x));
    }
	static void DEBUG(long[] x) {
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
}
