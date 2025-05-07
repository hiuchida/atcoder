import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		final int N=3;
		int n=sc.nextInt();
		int[][] ary=new int[N][n];
		for (int i=0; i<N; i++) {
			for (int j=0; j<n; j++) {
				ary[i][j]=sc.nextInt();
			}
			Arrays.sort(ary[i]);
		}
//		for (int i=0; i<N; i++) {
//			System.out.println(Arrays.toString(ary[i]));
//		}
		long ans=0;
		for (int i=0; i<n; i++) {
			int b=ary[1][i];
			int i1=binarySearch(ary[0], b);
			int i2=binarySearch(ary[2], b+1);
			long x=i1;
			x*=n-i2;
//			System.out.println(i+" "+b+" "+i1+" "+i2+"  "+x);
			ans+=x;
		}
		System.out.println(ans);
	}
	static int binarySearch(int[] ary, int x) { //abc077_c: ary[mid]<x条件のバイナリーサーチ
		int lt=0;
		int rt=ary.length-1;
		while (lt<=rt) {
			int mid=(lt+rt)/2;
			if (ary[mid]<x) lt=mid+1;
			else rt=mid-1;
		}
		return lt;
	}
}
/*
2
1 5
2 4
3 6

3
1 1 1
2 2 2
3 3 3

6
3 14 159 2 6 53
58 9 79 323 84 6
2643 383 2 79 50 288
*/
