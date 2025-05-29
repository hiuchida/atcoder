import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		long n=sc.nextLong();
		for (long i=1; i*i<=n; i++) {
			if (n%i==0) {
				System.out.println(i);
				if (n/i != i) {
					System.out.println(n/i);
				}
			}
		}
	}
}
/*
12

827847039317
*/
