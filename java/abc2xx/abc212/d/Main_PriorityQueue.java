import java.util.*;
public class Main {
	static final boolean DEBUG = true;
	PriorityQueue<Long> que = new PriorityQueue<>();
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
				que.offer(v);
				break;
			case 2:
				v = nextInt();
				base+=v;
				break;
			case 3:
				v = que.poll();
				ans=v+base;
				System.out.println(ans);
				break;
			}
		}
	}
	long ans = 0;//Integer.MAX_VALUE;
/*
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
