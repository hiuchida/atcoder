import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] as = new int[n];
		for (int i=0; i<as.length; i++) {
			as[i] = sc.nextInt();
		}
		int[] at = new int[n];
		for (int i=0; i<at.length; i++) {
			at[i] = sc.nextInt();
		}
		int[] ans = new int[n];
		for (int i=0; i<n; i++) {
			int ii=(i+n-1)%n;
			int x=Math.min(at[ii], ans[ii]);
			ans[i] = Math.min(at[i], x+as[ii]);
		}
		for (int i=0; i<n; i++) {
			int ii=(i+n-1)%n;
			int x=Math.min(at[ii], ans[ii]);
			ans[i] = Math.min(at[i], x+as[ii]);
		}
		for (int i=0; i<n; i++) {
			System.out.println(ans[i]);
		}
	}
}
/*
3
4 1 5
3 10 100

4
100 100 100 100
1 1 1 1

4
1 2 3 4
1 2 4 7

8
84 87 78 16 94 36 87 93
50 22 63 28 91 60 64 27
*/
