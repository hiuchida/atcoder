import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ary=new int[n];
		long[] sum=new long[n+1];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
			sum[i+1]=sum[i]+ary[i];
		}
//		System.out.println(Arrays.toString(ary));
//		System.out.println(Arrays.toString(sum));
		long ans=0;
		for (int i=n-1; i>0; i--) {
//			System.out.println(i+" "+ary[i]+" "+sum[i]);
			long x=ary[i]*sum[i];
			ans+=x;
		}
		System.out.println(ans);
	}
}
/*
3
4 2 3

2
9 45

10
7781 8803 8630 9065 8831 9182 8593 7660 7548 8617
*/
