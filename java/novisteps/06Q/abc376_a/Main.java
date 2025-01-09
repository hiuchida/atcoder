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
/*
6 5
1 3 7 8 10 12

3 2
0 2 4

10 3
0 3 4 6 9 12 15 17 19 20
*/
