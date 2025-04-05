import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		final int M=(int)1e9;
		int n=sc.nextInt();
		int m=sc.nextInt();
		long x=1;
		long ans=0;
		for (int i=0; i<=m; i++) {
			if (x>M || ans+x>M) {
				System.out.println("inf");
				System.exit(0);
			}
			ans+=x;
			x*=n;
		}
		System.out.println(ans);
	}
}
/*
7 3

1000000 2

999999999 1

998244353 99
*/
