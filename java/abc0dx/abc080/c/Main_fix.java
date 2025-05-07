import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		final int N=10;
		int n=sc.nextInt();
		int[][] af=new int[n][N];
		for (int i=0; i<n; i++) {
			for (int j=0; j<N; j++) {
				af[i][j]=sc.nextInt();
			}
		}
		int[][] ap=new int[n][N+1];
		for (int i=0; i<n; i++) {
			for (int j=0; j<N+1; j++) {
				ap[i][j]=sc.nextInt();
			}
		}
//		for (int i=0; i<n; i++) {
//			System.out.println(Arrays.toString(af[i]));
//		}
//		for (int i=0; i<n; i++) {
//			System.out.println(Arrays.toString(ap[i]));
//		}
		long ans=Long.MIN_VALUE;
		for (int i=0; i< 1 << N; i++) {
			int[] ad=new int[n];
			for (int j=0; j<N; j++) {
				int mask=1 << j;
				if ((i&mask)>0) {
					for (int k=0; k<n; k++) {
						if (af[k][j]>0) ad[k]++;
					}
				}
			}
			int cnt=0;
			long sum=0;
			for (int k=0; k<n; k++) {
				cnt+=ad[k];
				sum+=ap[k][ad[k]];
			}
//			System.out.println(i+" "+cnt+" "+sum);
			if (cnt>0) {
				ans=Math.max(ans, sum);
			}
		}
		System.out.println(ans);
	}
}
/*
1
1 1 0 1 0 0 0 1 0 1
3 4 5 6 7 8 9 -2 -3 4 -2

2
1 1 1 1 1 0 0 0 0 0
0 0 0 0 0 1 1 1 1 1
0 -2 -2 -2 -2 -2 -1 -1 -1 -1 -1
0 -2 -2 -2 -2 -2 -1 -1 -1 -1 -1

3
1 1 1 1 1 1 0 0 1 1
0 1 0 1 1 1 1 0 1 0
1 0 1 1 0 1 0 1 0 1
-8 6 -2 -8 -8 4 8 7 -6 2 2
-9 2 0 1 7 -5 0 -2 -6 5 5
6 -6 7 -9 6 -5 8 0 -9 -7 -7
*/
