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
			int from = r;
			int to = t;
			int ng = l;
			switch (h) {
			case "R":
				break;
			case "L":
				from = l;
				ng = r;
				break;
			}
			if (from < ng && ng < to) {
				ans += from + n - to;
			} else if (to < ng && ng < from) {
				ans += to + n - from;
			} else {
				ans += Math.abs(from - to);
			}
			switch (h) {
			case "R":
				r = t;
				break;
			case "L":
				l = t;
				break;
			}
		}
		System.out.println(ans);
	}
}
