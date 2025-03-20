import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long a = sc.nextLong();
		long b = sc.nextLong();
		while (a>0 && b>0) {
			long a0=a%10;
			long b0=b%10;
			if (a0+b0>9) {
				System.out.println("Hard");
				System.exit(0);
			}
			a/=10;
			b/=10;
		}
		System.out.println("Easy");
	}
}
/*
229 390

123456789 9876543210
*/
