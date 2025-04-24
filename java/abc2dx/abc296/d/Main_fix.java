import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		long n=sc.nextLong();
		long m=sc.nextLong();
		if (n<m/n) {
			System.out.println(-1);
			System.exit(0);
		}
		long ans=Long.MAX_VALUE;
		long k=1;
		while (k*k<m) k++;
		for (long a=1; a<=k; a++) {
			if (a>n) break;
			for (long b=m/a-10; b<m/a+10; b++) {
				if (b>n) continue;
				if (a*b>=m) {
//					System.out.println(a+" "+b+" "+(a*b));
					ans=Math.min(ans, a*b);
				}
			}
		}
		if (ans==Long.MAX_VALUE) ans=-1;
		System.out.println(ans);
	}
}
/*
5 7

2 5

100000 10000000000
*/
/*
10000 10000000000

10 100000000007

1000000 1000000000000
*/
