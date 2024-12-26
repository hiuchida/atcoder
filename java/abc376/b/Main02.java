import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int q = sc.nextInt();
		int l = 1;
		int r = 2;
		int ans = 0;
		for (int i = 0; i < q; i++) {
			String h = sc.next();
			int t = sc.nextInt();
			while (l > r) r += n;
			switch (h) {
			case "R":
				while (t < l) t += n;
				ans += Math.abs(t - r);
				r = t;
				break;
			case "L":
				while (t > r) t -= n;
				ans += Math.abs(t - l);
				l = t;
				break;
			}
			while (l < 0) l += n;
			while (r < 0) r += n;
			l %= n;
			r %= n;
		}
		System.out.println(ans);
	}
}
