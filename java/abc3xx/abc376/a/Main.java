import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int c = sc.nextInt();
		int tt = -9999;
		int ans = 0;
		for (int i = 0; i < n; i++) {
			int t = sc.nextInt();
			if (t - tt >= c) {
				ans++;
				tt = t;
			}
		}
		System.out.println(ans);
	}
}
