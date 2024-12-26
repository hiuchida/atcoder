import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int c = sc.nextInt();
		int tt = sc.nextInt();
		int ans = 1;
		for (int i = 1; i < n; i++) {
			int t = sc.nextInt();
			if (t - tt >= c) {
				ans++;
				tt = t;
			}
		}
		System.out.println(ans);
	}
}
