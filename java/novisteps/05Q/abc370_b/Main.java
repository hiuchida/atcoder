import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] ary = new int[n+1][n+1];
		for (int i=1; i<=n; i++) {
			for (int j=1; j<=i; j++) {
				int a = sc.nextInt();
				ary[i][j] = a;
			}
		}
//		for (int i=1; i<=n; i++) {
//			System.out.println(Arrays.toString(ary[i]));
//		}
		int ans = 1;
		for (int i=1; i<=n; i++) {
			int t;
			if (ans >= i) {
				t=ary[ans][i];
			} else {
				t=ary[i][ans];
			}
			ans=t;
		}
		System.out.println(ans);
	}
}
/*
4
3
2 4
3 1 2
2 1 2 4

5
5
5 5
5 5 5
5 5 5 5
5 5 5 5 5

6
2
1 5
1 6 3
2 6 1 4
2 1 1 1 6
5 6 1 2 2 5
*/