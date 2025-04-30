import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int q=sc.nextInt();
		String s=sc.next();
		StringHash sh=new StringHash(s);
		for (int qq=0; qq<q; qq++) {
			int a=sc.nextInt();
			int b=sc.nextInt();
			int c=sc.nextInt();
			int d=sc.nextInt();
			long h1=sh.hashValue(a, b);
			long h2=sh.hashValue(c, d);
			if (h1==h2) System.out.println("Yes");
			else System.out.println("No");
		}
	}
	static class StringHash { //StringHash20250501
		static final int M = Integer.MAX_VALUE;
		long[] pow;
		long[] ary;
		StringHash(String S) {
			int N = S.length();
			int[] T = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				T[i] = (int)(S.charAt(i - 1) - 'a') + 1;
			}
			pow = new long[N + 1];
			pow[0] = 1;
			for (int i = 1; i <= N; i++) {
				pow[i] = pow[i - 1] * 100 % M;
			}
			ary = new long[N + 1];
			ary[0] = 1;
			for (int i = 1; i <= N; i++) {
				ary[i] = (ary[i - 1] * 100 + T[i]) % M;
			}
		}
		// S[l, r] のハッシュ値を返す関数
		long hashValue(int l, int r) {
			long val = ary[r] - ary[l - 1] * pow[r - l + 1] % M;
			if (val < 0) val += M;
			return val;
		}
	}
}
/*
7 3
abcbabc
1 3 5 7
1 5 2 6
1 2 6 7
*/
