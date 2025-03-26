import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long n = sc.nextInt();
		long x=2;
		long nn=n*n;
		for (long i=1; i<=n; i++) {
			if (x>nn) {
				System.out.println("Yes");
				System.exit(0);
			}
			x*=2;
		}
		System.out.println("No");
	}
}
/*
5

2

623947744
*/
