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
		int n = sc.nextInt();
		TreeSet<String> set = new TreeSet<>();
		for (int i=1; i<=n; i++) {
			String s = sc.next();
			if (!set.contains(s)) {
				System.out.println(i);
				set.add(s);
			}
		}
//		DEBUG(set);
	}
}
