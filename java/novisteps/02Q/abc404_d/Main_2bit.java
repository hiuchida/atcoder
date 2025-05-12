import java.util.*;
public class Main {
	static int n;
	static int m;
	static int[] ac;
	static int[][] ary;
	static long ans=Long.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		ac=new int[n];
		for (int i=0; i<n; i++) {
			ac[i]=sc.nextInt();
		}
		ary=new int[n][m];
		for (int i=0; i<m; i++) {
			int k=sc.nextInt();
			for (int j=0; j<k; j++) {
				int a=sc.nextInt();
				ary[a-1][i]=1;
			}
		}
//		for (int y=0; y<n; y++) {
//			System.out.println(Arrays.toString(ary[y]));
//		}
		for (int i=0; i < 1 << 2*n; i++) {
			boolean bok=true;
			for (int j=0; j<n; j++) {
				int mask=3 << 2*j;
				if ((i&mask)==mask) bok=false;
			}
			if (!bok) continue;
			long sum=0;
			int[] flag=new int[m];
			for (int j=0; j<n; j++) {
				int mask=3 << 2*j;
				int val1=1 << 2*j;
				int val2=2 << 2*j;
				if ((i&mask)==val1) {
					sum+=ac[j];
					for (int k=0; k<m; k++) {
						flag[k]+=ary[j][k];
					}
				} else if ((i&mask)==val2) {
					sum+=2L*ac[j];
					for (int k=0; k<m; k++) {
						flag[k]+=2*ary[j][k];
					}
				}
			}
			bok=true;
			for (int j=0; j<m; j++) {
				if (flag[j]<2) bok=false;
			}
			if (!bok) sum=Long.MAX_VALUE;
//			System.out.println(i+" "+Arrays.toString(flag)+" "+sum);
			ans=Math.min(ans, sum);
		}
		System.out.println(ans);
	}
}
/*
4 3
1000 300 700 200
3 1 3 4
3 1 2 4
2 1 3

7 6
500 500 500 500 500 500 1000
3 1 2 7
3 2 3 7
3 3 4 7
3 4 5 7
3 5 6 7
3 6 1 7
*/
