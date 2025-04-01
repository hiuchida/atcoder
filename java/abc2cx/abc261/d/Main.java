import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		TreeMap<Integer,Integer> map=new TreeMap<>();
		for (int i=0; i<m; i++) {
			int c = sc.nextInt();
			int y = sc.nextInt();
			map.put(c, y);
		}
//		System.out.println(Arrays.toString(ary));
//		System.out.println(map);
		long[][] dp=new long[n+1][n+1];
		for (int j=0; j<n; j++) {
			for (int i=0; i<n; i++) {
				if (i>j) continue;
				dp[0][j+1]=Math.max(dp[0][j+1], dp[i][j]);
				Integer bp=map.get(i+1);
				if (bp==null) bp=0;
				dp[i+1][j+1]=Math.max(dp[i+1][j+1], dp[i][j]+ary[j]+bp);
			}
		}
//		for (int i=0; i<=n; i++) {
//			for (int j=0; j<=n; j++) {
//				System.out.print(String.format("%2d ", dp[i][j]));
//				System.out.println(Arrays.toString(dp[i]));
//			}
//			System.out.println();
//		}
		long ans=0;
		for (int i=0; i<=n; i++) {
			ans=Math.max(ans, dp[i][n]);
		}
		System.out.println(ans);
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
