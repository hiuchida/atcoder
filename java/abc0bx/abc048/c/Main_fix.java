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
		int[] sum=new int[n];
		for (int i=0; i<n; i++) {
			sum[i]=ary[i];
			if (i+1<n) sum[i]+=ary[i+1];
		}
//		System.out.println(Arrays.toString(ary));
//		System.out.println(Arrays.toString(sum));
		long ans=0;
		for (int i=0; i<n; i++) {
			int d=sum[i]-x;
			if (d>0) {
				ans+=d;
				sum[i]-=d;
				if (i+1<n) {
					int d2=Math.min(sum[i+1], d);
					sum[i+1]-=d2;
				}
			}
//			System.out.println(i+" "+Arrays.toString(sum)+" "+ans);
		}
		System.out.println(ans);
	}
}
/*
3 3
2 2 2

6 1
1 6 1 2 0 4

5 9
3 1 4 1 5

2 0
5 5
*/
