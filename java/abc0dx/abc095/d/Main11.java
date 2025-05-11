import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		long c=sc.nextLong();
		long[] ax=new long[n];
		int[] av=new int[n];
		for (int i=0; i<n; i++) {
			ax[i]=sc.nextLong();
			av[i]=sc.nextInt();
		}
		long[] sum1=new long[n+1];
		for (int i=0; i<n; i++) {
			sum1[i+1]=sum1[i]+av[i];
		}
		long[] ay=new long[n];
		long[] sum2=new long[n+1];
		for (int i=0; i<n; i++) {
			int j=n-1-i;
			ay[i]=c-ax[j];
			sum2[i+1]=sum2[i]+av[j];
		}
//		System.out.println(Arrays.toString(ax));
//		System.out.println(Arrays.toString(ay));
//		System.out.println(Arrays.toString(sum1));
//		System.out.println(Arrays.toString(sum2));
		long ans=0;
		for (int i=1; i<=n; i++) {
			long v1=sum1[i]-ax[i-1];
//			System.out.println(i+" "+v1);
			ans=Math.max(ans, v1);
			for (int j=1; i+j<=n; j++) {
				long v2=sum2[j]-ay[j-1];
				long z=Math.min(ax[i-1], ay[j-1]);
//				System.out.println(i+" "+j+" "+v1+" "+v2+" "+z);
				ans=Math.max(ans, v1+v2-z);
			}
		}
		System.out.println(ans);
	}
}
/*
3 20
2 80
9 120
16 1

3 20
2 80
9 1
16 120

1 100000000000000
50000000000000 1

15 10000000000
400000000 1000000000
800000000 1000000000
1900000000 1000000000
2400000000 1000000000
2900000000 1000000000
3300000000 1000000000
3700000000 1000000000
3800000000 1000000000
4000000000 1000000000
4100000000 1000000000
5200000000 1000000000
6600000000 1000000000
8000000000 1000000000
9300000000 1000000000
9700000000 1000000000
*/
