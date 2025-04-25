import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		int[][] ary=new int[m][n];
		for (int j=0; j<m; j++) {
			for (int i=0; i<n; i++) {
				ary[j][i]=sc.nextInt();
			}
		}
//		for (int j=0; j<m; j++) {
//			System.out.println(Arrays.toString(ary[j]));
//		}
		int[][] mat=new int[n+1][n+1];
		for (int j=0; j<m; j++) {
			for (int i=1; i<n; i++) {
				int a=ary[j][i-1];
				int b=ary[j][i];
				mat[a][b]++;
				mat[b][a]++;
			}
		}
//		for (int j=1; j<=n; j++) {
//			System.out.println(Arrays.toString(mat[j]));
//		}
		int ans=0;
		for (int j=1; j<=n; j++) {
			for (int i=j+1; i<=n; i++) {
				if (mat[j][i]==0) ans++;
			}
		}
		System.out.println(ans);
	}
}
/*
4 2
1 2 3 4
4 3 1 2

3 3
1 2 3
3 1 2
1 2 3

10 10
4 10 7 2 8 3 9 1 6 5
3 6 2 9 1 8 10 7 4 5
9 3 4 5 7 10 1 8 2 6
7 3 1 8 4 9 5 6 2 10
5 2 1 4 10 7 9 8 3 6
5 8 1 6 9 3 2 4 7 10
8 10 3 4 5 7 2 9 6 1
3 10 2 7 8 5 1 4 9 6
10 6 1 5 4 2 3 8 9 7
4 5 9 1 8 2 7 6 3 10
*/
