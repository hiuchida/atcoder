import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long n = sc.nextLong();
		long ans=0;
		for (long c=n; c>=1; c--) {
			for (long b=1; b<=c; b++) {
				if (c>n/b) break;
				long cb=c*b;
				for (long a=1; a<=b; a++) {
					if (cb>n/a) break;
					long cba=cb*a;
					if (cba<=n) ans++;
				}
			}
		}
		System.out.println(ans);
	}
}
/*
4

100

100000000000
*/
