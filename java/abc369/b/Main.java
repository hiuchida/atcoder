import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long ans = 0;
		int l=0;
		int r=0;
		for (int i=0; i<n; i++) {
			int a = sc.nextInt();
			String s = sc.next();
			if ("L".equals(s)) {
				if (l==0) {
					l=a;
				} else {
					ans += Math.abs(l-a);
					l=a;
				}
			} else {
				if (r==0) {
					r=a;
				} else {
					ans += Math.abs(r-a);
					r=a;
				}
			}
		}
		System.out.println(ans);
	}
}
