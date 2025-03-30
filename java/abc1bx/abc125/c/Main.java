import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] ary=new int[n];
		for (int i=0; i<n; i++) {
			ary[i]=sc.nextInt();
		}
		long[] lt=new long[n+1];
		long gcd=0;
		for (int i=0; i<n; i++) {
			lt[i+1]=gcd(gcd, ary[i]);
			gcd=lt[i+1];
		}
		long[] rt=new long[n+1];
		gcd=0;
		for (int i=n-1; i>=0; i--) {
			rt[i]=gcd(gcd, ary[i]);
			gcd=rt[i];
		}
//		System.out.println(Arrays.toString(ary));
//		System.out.println(Arrays.toString(lt));
//		System.out.println(Arrays.toString(rt));
		long ans=0;
		for (int i=0; i<n; i++) {
			ans=Math.max(ans, gcd(lt[i], rt[i+1]));
		}
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
7 6 8

3
12 15 18

2
1000000000 1000000000
*/
