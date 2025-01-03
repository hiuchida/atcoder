import java.util.*;
public class Main {
	static void DEBUG(Object x) {
//		System.out.println(x);
	}
	static void DEBUG(long x) {
		DEBUG(""+x);
    }
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary = new int[n];
		for (int i=0; i<n; i++) {
			int a = sc.nextInt();
			ary[i] = a;
		}
		DEBUG(Arrays.toString(ary));
		long ans = Integer.MAX_VALUE;
		for (int i=0; i < 1 << (n-1); i++) {
			long ans0 = 0;
			long or0 = ary[0];
			for (int j=1; j<n; j++) {
				int mask = 1 << (j-1);
				if ((i & mask) > 0) {
					ans0 ^= or0;
					DEBUG(i + "," + j + " " + or0 + " " + ans0);
					or0 = ary[j];
				} else {
					or0 |= ary[j];
				}
			}
			ans0 ^= or0;
			DEBUG(i + " " + or0 + " " + ans0 + " last");
			ans = Math.min(ans, ans0);
		}
		System.out.println(ans);
	}
}
