import java.util.*;
public class Main {
	static int n;
	static long[][] ary;
	static long ans=Long.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		int m = sc.nextInt();
		ary=new long[n+1][n+1];
		for (int i=0; i<=n; i++) {
			for (int j=0; j<=n; j++) {
				ary[i][j]=-1;
			}
		}
		for (int i=0; i<m; i++) {
			int u=sc.nextInt();
			int v=sc.nextInt();
			long w=sc.nextLong();
			ary[u][v]=w;
			ary[v][u]=w;
		}
//		for (int i=0; i<=n; i++) {
//			for (int j=0; j<=n; j++) {
//				System.out.printf("%02d ", ary[i][j]);
//			}
//			System.out.println();
//		}
		boolean[] flag=new boolean[n+1];
		dfs(1, flag, 0);
		System.out.println(ans);
	}
	static void dfs(int idx, boolean[] flag, long xor) {
		flag[idx]=true;
		if (idx==n) {
			ans=Math.min(ans, xor);
		}
		for (int i=1; i<=n; i++) {
			if (flag[i]) continue;
			if (ary[idx][i] < 0) continue;
			long xor2=xor^ary[idx][i];
			dfs(i, flag, xor2);
		}
		flag[idx]=false;
	}
}
/*
4 4
1 2 3
2 4 5
1 3 4
3 4 7

4 3
1 2 1
2 3 2
3 4 4

7 10
1 2 726259430069220777
1 4 988687862609183408
1 5 298079271598409137
1 6 920499328385871537
1 7 763940148194103497
2 4 382710956291350101
3 4 770341659133285654
3 5 422036395078103425
3 6 472678770470637382
5 7 938201660808593198
*/
