import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[][] ary=new int[n][n];
		for (int mm=0; mm<m; mm++) {
			int k = sc.nextInt();
			int[] ax=new int[k];
			for (int i=0; i<k; i++) {
				ax[i]=sc.nextInt()-1;
			}
			for (int i=0; i<k; i++) {
				for (int j=i+1; j<k; j++) {
					int u=ax[i];
					int v=ax[j];
					ary[u][v]=1;
					ary[v][u]=1;
				}
			}
		}
		for (int i=0; i<n; i++) {
			for (int j=i+1; j<n; j++) {
				if (ary[i][j]==0) {
					System.out.println("No");
					System.exit(0);
				}
			}
		}
		System.out.println("Yes");
	}
}
/*
3 3
2 1 2
2 2 3
2 1 3

4 2
3 1 2 4
3 2 3 4
*/
