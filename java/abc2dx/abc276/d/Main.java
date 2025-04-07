import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] ary=new int[n];
		long gcd=0;
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
			gcd=gcd(gcd, ary[i]);
		}
		Arrays.sort(ary);
//		System.out.println(Arrays.toString(ary));
//		System.out.println(gcd);
		long ans=0;
		int min=Integer.MAX_VALUE;
		int max=0;
		for (int i=0; i<n; i++) {
			ary[i]/=gcd;
			while (ary[i]%2==0) {
				ary[i]/=2;
				ans++;
			}
			while (ary[i]%3==0) {
				ary[i]/=3;
				ans++;
			}
			min=Math.min(min, ary[i]);
			max=Math.max(max, ary[i]);
		}
		if (min!=max) ans=-1;
		System.out.println(ans);
	}
	static long gcd(long a, long b) {
		if (b<a) {
			long t=a;
			a=b;
			b=t;
		}
		while (a>0) {
			long t=b%a;
			b=a;
			a=t;
		}
		return b;
	}
}
/*
3
1 4 3

3
2 7 6

6
1 1 1 1 1 1
*/
