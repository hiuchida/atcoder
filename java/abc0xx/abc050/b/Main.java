import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary = new int[n];
		for (int i=0; i<ary.length; i++) {
			ary[i] = sc.nextInt();
		}
		int m = sc.nextInt();
		for (int i=0; i<m; i++) {
			int p = sc.nextInt();
			int x = sc.nextInt();
			int ans = 0;
			for (int j=0; j<n; j++) {
				int v=ary[j];
				if (p-1==j) v=x;
				ans+=v;
			}
			System.out.println(ans);
		}
	}
}
/*
3
2 1 4
2
1 1
2 3

5
7 2 3 8 5
3
4 2
1 7
4 13
*/
