import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		int[][] ary=new int[m][n];
		for (int i=0; i<m; i++) {
			int c=sc.nextInt();
			for (int j=0; j<c; j++) {
				int a=sc.nextInt();
				ary[i][a-1]++;
			}
		}
//		for (int i=0; i<m; i++) {
//			System.out.println(Arrays.toString(ary[i]));
//		}
		int ans=0;
		for (int i=0; i < 1 << m; i++) {
			int[] sum=new int[n];
			for (int j=0; j<m; j++) {
				int mask=1 << j;
				if ((i&mask)>0) {
					for (int k=0; k<n; k++) {
						sum[k]+=ary[j][k];
					}
				}
			}
			boolean bng=false;
			for (int k=0; k<n; k++) {
				if (sum[k]==0) bng=true;
			}
			if (!bng) ans++;
		}
		System.out.println(ans);
	}
}
/*
3 3
2
1 2
2
1 3
1
2

4 2
2
1 2
2
1 3

6 6
3
2 3 6
3
2 4 6
2
3 6
3
1 5 6
3
1 3 6
2
1 4
*/
