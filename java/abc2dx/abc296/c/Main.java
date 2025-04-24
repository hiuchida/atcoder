import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int x=sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		Arrays.sort(ary);
		int[] dif=new int[n];
		for (int i=1; i<n; i++) {
			dif[i]=ary[i]-ary[i-1];
		}
		long[] sum=new long[n];
		for (int i=1; i<n; i++) {
			sum[i]=sum[i-1]+dif[i];
		}
//		System.out.println(Arrays.toString(ary));
//		System.out.println(Arrays.toString(dif));
//		System.out.println(Arrays.toString(sum));
		if (x<0) x=-x;
		for (int i=0; i<n; i++) {
			long v=sum[i]+x;
			int idx=Arrays.binarySearch(sum, v);
			if (idx>=0) {
				System.out.println("Yes");
				System.exit(0);
			}
		}
		System.out.println("No");
	}
}
/*
6 5
3 1 4 1 5 9

6 -4
-2 -7 -1 -8 -2 -8

2 0
141421356 17320508
*/
