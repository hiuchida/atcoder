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
/*
4
3 L
6 R
9 L
1 R

3
2 L
2 L
100 L

8
22 L
75 L
26 R
45 R
72 R
81 R
47 L
29 R
*/
