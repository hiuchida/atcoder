import java.util.*;
public class Main {
	static long ans = Integer.MAX_VALUE;
	static int n;
	static int s = 0;
	static int[] ary;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		ary = new int[n];
		for (int i=0; i<n; i++) {
			int k = sc.nextInt();
			ary[i] = k;
			s += k;
		}
		dfs(0, 0);
		System.out.println(ans);
	}
	static void dfs(int i, int a) {
		if (i == n) {
			ans = Math.min(ans, Math.max(a, s-a));
			return;
		}
		int v = ary[i];
		dfs(i+1, a+v);
		dfs(i+1, a);
	}
}
