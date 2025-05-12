import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			long m = 998244353;
			long ans = 0;
			for (int i = 1; i <= a; i++) {
				for (int j = 1; j <= b; j++) {
					for (int k = 1; k <= c; k++) {
						long x = (long) (i * j * k) % m;
						ans = (ans + x) % m;
					}
				}
			}
			System.out.println(ans);
		}
	}

}
