import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long n = sc.nextLong();
		long ans=0;
		for (long a=1; a*a*a<=n; a++) {
			for (long b=a; a*b*b<=n; b++) {
				ans+=n/a/b-b+1;
//				for (long c=b; a*b*c<=n; c++) {
//					ans++;
//				}
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
