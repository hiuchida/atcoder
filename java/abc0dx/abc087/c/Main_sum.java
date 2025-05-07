import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		final int N=2;
		int n=sc.nextInt();
		int[][] ary=new int[N][n];
		int[][] sum=new int[N][n+1];
		for (int i=0; i<N; i++) {
			for (int j=0; j<n; j++) {
				ary[i][j]=sc.nextInt();
				sum[i][j+1]=sum[i][j]+ary[i][j];
			}
		}
//		for (int i=0; i<N; i++) {
//			System.out.println(Arrays.toString(ary[i]));
//			System.out.println(Arrays.toString(sum[i]));
//		}
		int ans=0;
		for (int i=0; i<n; i++) {
			int x1=sum[0][i+1]-sum[0][0]+sum[1][n]-sum[1][i];
//			int x=0;
//			for (int j=0; j<=i; j++) x+=ary[0][j];
//			for (int j=i; j<n; j++) x+=ary[1][j];
//			if (x1!=x) System.out.println(i+" "+x1+" "+x);
			ans=Math.max(ans, x1);
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
