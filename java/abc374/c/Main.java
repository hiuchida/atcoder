import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary = new int[n];
		for (int i=0; i<n; i++) {
			int k = sc.nextInt();
			ary[i] = k;
		}
		long ans = Integer.MAX_VALUE;
		int ans2 = 0;
		for (int i=0; i<1024*1024; i++) {
			int a = 0;
			int b = 0;
			for (int j=0; j<20 && j<n; j++) {
				int mask = 1 << j;
				if ((i & mask) > 0) {
					a += ary[j];
				} else {
					b += ary[j];
				}
			}
			if (ans > Math.abs(a - b)) {
				ans = Math.abs(a - b);
				ans2 = Math.max(a, b);
			}
			if (ans == 0) break;
		}
		System.out.println(ans2);
	}
}
