import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long k = sc.nextLong();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			int a = sc.nextInt();
			ary[i]=a;
		}
		long[] sum=new long[n+1];
		for (int i=0; i<n; i++) {
			sum[i+1]=sum[i]+ary[i];
		}
		Arrays.sort(sum);
//		System.out.println(Arrays.toString(ary));
//		System.out.println(Arrays.toString(sum));
		long ans=0;
		int l=0;
		int r=0;
		while (l<=n) {
			long a=sum[l];
			while (r<=n) {
				long b=sum[r];
//				System.out.println(l+" "+r+" "+a+" "+b);
				if (b-a==k) ans++;
				else if (b-a>k) {
					r--;
					break;
				}
				r++;
			}
			l++;
		}
		System.out.println(ans);
	}
}
/*
6 5
8 -3 5 7 0 -4

2 -1000000000000000
1000000000 -1000000000
*/
