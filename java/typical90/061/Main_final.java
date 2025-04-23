import java.util.*;
public class Main {
	static final boolean DEBUG = true;
	static void DEBUG(Object x) {
		if (DEBUG) System.out.println(x);
	}
	static void DEBUG(long x) {
		if (DEBUG) DEBUG(""+x);
    }
	static void DEBUG(int[] x) {
		if (DEBUG) DEBUG(Arrays.toString(x));
    }
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int q = sc.nextInt();
		MyDeque que = new MyDeque(q, 0);
		DEBUG(que);
		for (int i=0; i<q; i++) {
			int t = sc.nextInt();
			int x = sc.nextInt();
			switch (t) {
			case 1:
				que.addFirst(x);
				DEBUG(que);
				break;
			case 2:
				que.addLast(x);
				DEBUG(que);
				break;
			case 3:
				int v=que.get(x);
				System.out.println(v);
				break;
			}
		}
	}
	static class MyDeque { //MyDeque_int20250105
		int[] ary;
		int head;
		int tail;
		MyDeque(int n, int i) {
			ary = new int[n+1];
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
			tail++;
			if (tail >= ary.length) tail -= ary.length;
		}
		int get(int i) {
			i += head-1;
			if (i >= ary.length) i -= ary.length;
			return ary[i];
		}
		@Override
		public String toString() {
			return Arrays.toString(ary) + " " + head + " " + tail;
		}
	}
}
/*
6
1 3
1 2
2 4
2 5
2 6
1 1
*/
