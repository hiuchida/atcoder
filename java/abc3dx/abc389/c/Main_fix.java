import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	void solve() {
		int q = nextInt();
		MyDeque que=new MyDeque(q, 0);
		for (int i=0; i<q; i++) {
			int c = nextInt();
			int l;
			int k;
			switch (c) {
			case 1:
				l = nextInt();
				que.addLast(l);
				DEBUG(que);
				break;
			case 2:
				que.removeFirst();
				DEBUG(que);
				break;
			case 3:
				k = nextInt();
				if (k==1) System.out.println(0);
				else {
					k-=2;
					long ans=que.ary[que.head+k];
					if (que.head>0) {
						ans-=que.ary[que.head-1];
					}
					System.out.println(ans);
				}
				break;
			}
		}
	}
	static class MyDeque { //MyDeque_long20250316
		long[] ary;
		int head;
		int tail;
		MyDeque(int n, int i) {
			ary = new long[n+1];
			head = i;
			tail = i;
		}
		void addFirst(int x) {
			head--;
			if (head < 0) head = ary.length - 1;
			ary[head]=x;
		}
		void addLast(int x) {
			ary[tail]=x;
			if (tail>0) {
				ary[tail]+=ary[tail-1];
			}
			tail++;
			if (tail >= ary.length) tail -= ary.length;
		}
		long removeFirst() {
			long x=ary[head];
			head++;
			if (head >= ary.length) head -= ary.length;
			return x;
		}
		long get(int i) {
			i += head-1;
			if (i >= ary.length) i -= ary.length;
			return ary[i];
		}
		@Override
		public String toString() {
			return Arrays.toString(ary) + " " + head + " " + tail;
		}
	}
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
/*
7
1 5
1 7
3 2
1 3
1 4
2
3 3

3
1 1
2
1 3

10
1 15
1 10
1 5
2
1 5
1 10
1 15
2
3 4
3 2
*/
