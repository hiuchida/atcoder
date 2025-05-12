import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
//		System.out.println(Arrays.toString(ary));
		if (check(ary, n, m)) {
			System.out.println(0);
			System.exit(0);
		}
		int ans;
		for (ans=1; ans<n; ans++) {
			if (check(ary, n-ans, m)) {
				System.out.println(ans);
				System.exit(0);
			}
		}
		System.out.println(ans);
	}
	static boolean check(int[] ary, int n, int m) {
		int[] cnt=new int[m+1];
		for (int i=0; i<n; i++) {
			int a=ary[i];
			if (a<=m) cnt[a]++;
		}
//		System.out.println(Arrays.toString(cnt));
		for (int i=1; i<=m; i++) {
			if (cnt[i]==0) return true;
		}
		return false;
	}
}
/*
5 3
3 2 3 1 2

4 3
1 3 1 3

10 4
1 3 3 4 2 1 3 1 2 4
*/
/*
4 2
1 2 1 1
*/
