import java.util.*;
public class Main {
	static final boolean DEBUG = false;
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
		MyDeque que = new MyDeque(q);
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
	static class MyDeque { //unused MyDeque_int
		int[] ary;
		int head;
		int tail;
		MyDeque(int n) {
			ary = new int[n*2];
			head = n;
			tail = n;
		}
		void addFirst(int x) {
			head--;
			ary[head]=x;
		}
		void addLast(int x) {
			ary[tail]=x;
			tail++;
		}
		int get(int i) {
			return ary[head+i-1];
		}
		@Override
		public String toString() {
			return Arrays.toString(ary) + " " + head + " " + tail;
		}
	}
}
