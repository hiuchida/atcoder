import java.util.*;
public class Main {
	static int n;
	static int[] ary;
	static TreeMap<Integer,Integer> map;
	static long[][] dp;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		int m = sc.nextInt();
		ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		map=new TreeMap<>();
		for (int i=0; i<m; i++) {
			int c = sc.nextInt();
			int y = sc.nextInt();
			map.put(c, y);
		}
//		System.out.println(Arrays.toString(ary));
//		System.out.println(map);
		dp=new long[n+1][n+1];
		long ans=0;
		for (int i=n; i>=0; i--) {
			ans=Math.max(ans, dfs(i, n));
		}
//		for (int j=0; j<n; j++) {
//			for (int i=0; i<n; i++) {
//				if (i>j) continue;
//				dp[0][j+1]=Math.max(dp[0][j+1], dp[i][j]);
//				Integer bp=map.get(i+1);
//				if (bp==null) bp=0;
//				dp[i+1][j+1]=Math.max(dp[i+1][j+1], dp[i][j]+ary[j]+bp);
//			}
//		}
//		for (int i=0; i<=n; i++) {
//			for (int j=0; j<=n; j++) {
//				System.out.print(String.format("%2d ", dp[i][j]));
//				System.out.println(Arrays.toString(dp[i]));
//			}
//			System.out.println();
//		}
		System.out.println(ans);
	}
	static long dfs(int i, int j) {
		if (j==0) return 0;
		if (dp[i][j]>0) return dp[i][j];
//		System.out.println(i+" "+j);
		if (i>0) {
			long v=dfs(i-1, j-1);
			Integer bp=map.get(i);
			if (bp==null) bp=0;
			v+=ary[j-1]+bp;
			dp[i][j]=v;
			return v;
		}
		long max=0;
		for (int h=0; h<j; h++) {
			long v=dfs(h, j-1);
			max=Math.max(max, v);
		}
		dp[i][j]=max;
		return max;
	}
}
/*
6 3
2 7 1 8 2 8
2 10
3 1
5 5

3 2
1000000000 1000000000 1000000000
1 1000000000
3 1000000000
*/
