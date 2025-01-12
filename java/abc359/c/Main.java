import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	void solve() {
		long sx = nextLong();
		long sy = nextLong();
		long tx = nextLong();
		long ty = nextLong();
		if (sx>tx) {
			long t=sx;
			sx=tx;
			tx=t;
			t=sy;
			sy=ty;
			ty=t;
		}
		boolean bs=false;
		boolean bt=false;
		if ((sx+sy)%2==0) bs=true;
		if ((tx+ty)%2==0) bt=true;
		DEBUG(sx+" "+sy+" "+tx+" "+ty + " "+bs+" "+bt);
		if (sx<tx && bs) sx++;
		DEBUG(sx+" "+sy+" "+tx+" "+ty);
		if (sx<tx && !bt) tx--;
		DEBUG(sx+" "+sy+" "+tx+" "+ty);
		ans=Math.abs(sy-ty);
		sx+=ans;
		sy=ty;
		bs=false;
		if ((sx+sy)%2==0) bs=true;
		if (sx<tx) {
			if (sx<tx && !bs) sx--;
			ans+=(tx-sx)/2;
		}
		System.out.println(ans);
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
5 0
2 5

3 1
4 1

2552608206527595 5411232866732612
771856005518028 7206210729152763
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
