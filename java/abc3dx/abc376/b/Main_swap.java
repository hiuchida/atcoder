import java.util.*;
public class Main {
	static int n;
	static int calc0(int from, int to, int ng) {
		int ans = 0;
		if (from < ng && ng < to) {
			ans += from + n - to;
		} else {
			ans += Math.abs(from - to);
		}
		return ans;
	}
	static int calc(int from, int to, int ng) {
		if (from <= to)
			return calc0(from, to, ng);
		else
			return calc0(to, from, ng);
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		int q = sc.nextInt();
		int l = 1;
		int r = 2;
		int ans = 0;
		for (int i = 0; i < q; i++) {
			String h = sc.next();
			int t = sc.nextInt();
			switch (h) {
			case "R":
				ans += calc(r, t, l);
				r = t;
				break;
			case "L":
				ans += calc(l, t, r);
				l = t;
				break;
			}
		}
		System.out.println(ans);
	}
}
