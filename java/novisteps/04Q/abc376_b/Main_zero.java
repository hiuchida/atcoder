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
			int tt;
			switch (h) {
			case "R":
				int ll = (l-r+n)%n;
				tt = (t-r+n)%n;
				if (tt < ll)
					ans += tt;
				else
					ans += n-tt;
				r = t;
				break;
			case "L":
				int rr = (r-l+n)%n;
				tt = (t-l+n)%n;
				if (tt < rr)
					ans += tt;
				else
					ans += n-tt;
				l = t;
				break;
			}
		}
		System.out.println(ans);
	}
}
