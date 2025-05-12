import java.util.*;
public class Main {
	static int n;
	static int m;
	static int[] ac;
	static int[][] ary;
	static long ans=Long.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		ac=new int[n];
		for (int i=0; i<n; i++) {
			ac[i]=sc.nextInt();
		}
		ary=new int[n][m];
		for (int i=0; i<m; i++) {
			int k=sc.nextInt();
			for (int j=0; j<k; j++) {
				int a=sc.nextInt();
				ary[a-1][i]=1;
			}
		}
//		for (int y=0; y<n; y++) {
//			System.out.println(Arrays.toString(ary[y]));
//		}
		int[] flag=new int[m];
		dfs(0, 0, flag);
		System.out.println(ans);
	}
	static void dfs(int i, long v, int[] flag) {
//		System.out.println(i+" "+v+" "+Arrays.toString(flag));
		if (i==n) {
			boolean bok=true;
			for (int j=0; j<m; j++) {
				if (flag[j]<2) bok=false;
			}
			if (bok) ans=Math.min(ans, v);
			return;
		}
		long v0=v;
		int[] flag0=Arrays.copyOf(flag, m);
		dfs(i+1, v0, flag0);
		long v1=v+ac[i];
		int[] flag1=add(flag, i);
		dfs(i+1, v1, flag1);
		long v2=v+2*ac[i];
		int[] flag2=add2(flag, i);
		dfs(i+1, v2, flag2);
	}
	static int[] add(int[] flag, int i) {
		int[] ans=Arrays.copyOf(flag, m);
		for (int j=0; j<m; j++) {
			ans[j]+=ary[i][j];
		}
		return ans;
	}
	static int[] add2(int[] flag, int i) {
		int[] ans=Arrays.copyOf(flag, m);
		for (int j=0; j<m; j++) {
			ans[j]+=2*ary[i][j];
		}
		return ans;
	}
}
/*
4 3
1000 300 700 200
3 1 3 4
3 1 2 4
2 1 3

7 6
500 500 500 500 500 500 1000
3 1 2 7
3 2 3 7
3 3 4 7
3 4 5 7
3 5 6 7
3 6 1 7
*/
