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
		Deque<Integer> que = new ArrayDeque<>();
		for (int i=0; i<q; i++) {
			int t = sc.nextInt();
			int x = sc.nextInt();
			switch (t) {
			case 1:
				que.addFirst(x);
				break;
			case 2:
				que.addLast(x);
				break;
			case 3:
				Iterator<Integer> itr=que.iterator();
				for (; x>1; x--) {
					itr.next();
				}
				int v=itr.next();
				System.out.println(v);
				break;
			}
		}
	}
}
