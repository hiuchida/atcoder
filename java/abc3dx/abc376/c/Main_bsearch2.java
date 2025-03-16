import java.util.*;
public class Main {
	static int n;
	static int[] arya;
	static int[] aryb;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		arya = new int[n];
		for (int i = 0; i < n; i++) {
			int a = sc.nextInt();
			arya[i] = a;
		}
		aryb = new int[n];
		for (int i = 0; i < n-1; i++) {
			int b = sc.nextInt();
			aryb[i] = b;
		}
		Arrays.sort(arya);
		Arrays.sort(aryb);
//		System.out.println(Arrays.toString(arya));
//		System.out.println(Arrays.toString(aryb));
		int ans = -1;
		int x = arya[n-1];
		if (!check(x)) {
			System.out.println(ans);
			return;
		}
		int ng = 0;
		while (ng + 1 < x) {
			int mid = (ng + x) / 2;
			if (check(mid)) {
				x = mid;
			} else {
				ng = mid;
			}
		}
		ans = x;
		System.out.println(ans);
	}
	static boolean check(int x) {
		int[] ary = new int[n];
		System.arraycopy(aryb, 0, ary, 0, n);
		ary[0] = x;
		Arrays.sort(ary);
		boolean rc = true;
		for (int i=0; i<n; i++) {
			if (arya[i] > ary[i]) {
				rc = false;
				break;
			}
		}
//		System.out.println(Arrays.toString(ary) + " x=" + x + " " + rc);
		return rc;
	}
}
