import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		final int N=2;
		int n=sc.nextInt();
		int[][] ary=new int[N][n];
		for (int i=0; i<N; i++) {
			for (int j=0; j<n; j++) {
				ary[i][j]=sc.nextInt();
			}
		}
//		for (int i=0; i<N; i++) {
//			System.out.println(Arrays.toString(ary[i]));
//		}
		int ans=0;
		for (int i=0; i<n; i++) {
			int sum=0;
			for (int j=0; j<=i; j++) sum+=ary[0][j];
			for (int j=i; j<n; j++) sum+=ary[1][j];
			ans=Math.max(ans, sum);
		}
		System.out.println(ans);
	}
}
/*
5
3 2 2 4 1
1 2 2 2 1

4
1 1 1 1
1 1 1 1

7
3 3 4 5 4 5 3
5 3 4 4 2 3 2

1
2
3
*/
