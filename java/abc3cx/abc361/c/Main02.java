import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	void solve() {
		n = nextInt();
		int k = nextInt();
		ary = nextIntAry(n);
		Arrays.sort(ary);
		DEBUG(ary);
		int l=0;
		int r=n-1;
		while (k>0) {
			int ld=ary[l+1]-ary[l];
			int rd=ary[r]-ary[r-1];
			DEBUG(ld + " " + rd);
			if (ld<rd) {
				r--;
				k--;
			} else if (ld>rd) {
				l++;
				k--;
			} else {
				for (int j=2; true; j++) {
					if (l+j>=r-j) {
						l++;
						k--;
						break;
					}
					int ld2=ary[l+j]-ary[l];
					int rd2=ary[r]-ary[r-j];
					if (ld2<rd2) {
						r--;
						k--;
						break;
					} else if (ld2>rd2) {
						l++;
						k--;
						break;
					}
				}
			}
		}
		ans=ary[r]-ary[l];
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
5 2
3 1 5 4 9

6 5
1 1 1 1 1 1

8 3
31 43 26 6 18 36 22 13

6 2
1 1 1 1 9 9
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
