import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[][] ary=new int[n+1][n+1];
		for (int i=0; i<m; i++) {
			int u=sc.nextInt();
			int v=sc.nextInt();
			ary[u][v]=1;
			ary[v][u]=1;
		}
//		for (int i=0; i<=n; i++) {
//			System.out.println(Arrays.toString(ary[i]));
//		}
		long ans=0;
		for (int a=1; a<=n; a++) {
			for (int b=a+1; b<=n; b++) {
				for (int c=b+1; c<=n; c++) {
					int v=ary[a][b]+ary[b][c]+ary[a][c];
					if (v==3) ans++;
				}
			}
		}
		System.out.println(ans);
	}
}
/*
5 6
1 5
4 5
2 3
1 4
3 5
2 5

3 1
1 2

7 10
1 7
5 7
2 5
3 6
4 7
1 5
2 4
1 3
1 6
2 7
*/
