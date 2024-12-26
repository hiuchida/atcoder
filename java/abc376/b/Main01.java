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
			switch (h) {
			case "R":
				if (r < t) {
					if (l < t) { // l=1 r=2 t=4 n=6 {3,4}
						ans += t-r;
						System.out.println("A " + l + " " + r + " " + ans);
					} else { // r=2 l=3 t=4 n=6 {1,6,5,4}
						ans += r+n-t;
						System.out.println("B " + l + " " + r + " " + ans);
					}
				} else if (r > t) {
					if (l < t) { // l=1 t=2 r=4 n=6 {3,2}
						ans += r-t;
						System.out.println("C " + l + " " + r + " " + ans);
					} else { // t=2 l=3 r=4 n=6 {5,6,1,2}
						ans += n-r+t;
						System.out.println("D " + l + " " + r + " " + ans);
					}
				}
				r = t;
				break;
			case "L":
				if (l < t) {
					if (r < t) { // l=2 r=3 t=4 n=6 {1,6,5,4}
						ans += l+n-t;
						System.out.println("E " + l + " " + r + " " + ans);
					} else { // l=1 t=4 r=5 n=6 {2,3,4}
						ans += t-l;
						System.out.println("F " + l + " " + r + " " + ans);
					}
				} else if (l > t) {
					if (r < t) { // t=1 l=3 r=4 n=6 {2,1}
						ans += l-t;
						System.out.println("G " + l + " " + r + " " + ans);
					} else { // t=2 r=3 l=4 n=6 {5,6,1,2}
						ans += n-l+t;
						System.out.println("H " + l + " " + r + " " + ans);
					}
				}
				l = t;
				break;
			}
		}
		System.out.println(ans);
	}
}
