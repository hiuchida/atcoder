import java.util.*;
public class Main {
	static final boolean DEBUG = true;
	void solve() {
		int a=nextInt();
		int b=nextInt();
		int c=nextInt();
		int d=nextInt();
		int e=nextInt();
		int f=nextInt();
		int g=nextInt();
		int h=nextInt();
		int i=nextInt();
		int j=nextInt();
		int k=nextInt();
		int l=nextInt();
		boolean b1=true;
		if (d<=g||j<=a) b1=false;
		boolean b2=true;
		if (e<=h||k<=b) b1=false;
		boolean b3=true;
		if (f<=i||l<=c) b1=false;
		if (b1&&b2&&b3) ok();
		ng();
	}
	long ans = 0;//Integer.MAX_VALUE;
	int n;
	long ln;
	String s;
	StringBuilder sb = new StringBuilder();
	List<String> list = new ArrayList<>();
	Deque<Integer> que = new ArrayDeque<>();
	TreeSet<Integer> set = new TreeSet<>();
	TreeMap<Integer,Integer> map = new TreeMap<>();
	int[] ary;
/*
0 0 0 4 5 6
2 3 4 5 6 7

0 0 0 2 2 2
0 0 2 2 2 4

0 0 0 1000 1000 1000
10 10 10 100 100 100
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
