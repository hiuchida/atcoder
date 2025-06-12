import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ary=new int[n];
		for (int i=1; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		long[] sum=new long[n+1];
		for (int i=1; i<n; i++) {
			sum[i]=sum[i-1]+ary[i];
		}
//		System.out.println(Arrays.toString(ary));
//		System.out.println(Arrays.toString(sum));
		int m=sc.nextInt();
		int pre=sc.nextInt();
		long ans=0;
		for (int i=1; i<m; i++) {
			int b=sc.nextInt();
			ans+=Math.abs(sum[b-1]-sum[pre-1]);
//			System.out.println(pre+" "+b+" "+sum[pre-1]+" "+sum[b-1]);
			pre=b;
		}
		System.out.println(ans);
	}
}
/*
4
8 6 9
6
2
1
3
2
3
4
*/
