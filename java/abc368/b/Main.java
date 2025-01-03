import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary = new int[n];
		for (int i=0; i<n; i++) {
			int a = sc.nextInt();
			ary[i] = a;
		}
		long ans = 0;
		while (true) {
			Arrays.sort(ary);
//			System.out.println(Arrays.toString(ary));
			if (ary[n-2] == 0) break;
			int a = ary[n-2];
			ary[n-2] -= a;
			ary[n-1] -= a;
			ans += a;
		}
		System.out.println(ans);
	}
}
