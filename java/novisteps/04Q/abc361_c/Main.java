import java.util.*;
public class Main {
	static final boolean DEBUG = false;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] ary = new int[n];
		for (int i=0; i<n; i++) {
			int a = sc.nextInt();
			ary[i] = a;
		}
		Arrays.sort(ary);
		long ans = Integer.MAX_VALUE;
		for (int i=0; i<=k; i++) {
			int l=ary[i];
			int r=ary[i+n-k-1];
			ans = Math.min(ans, r-l);
		}
		System.out.println(ans);
	}
}
/*
5 2
3 1 5 4 9

6 5
1 1 1 1 1 1

8 3
31 43 26 6 18 36 22 13
*/
