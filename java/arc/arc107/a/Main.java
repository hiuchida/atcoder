import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			long m = 998244353;
			long xa = (long) a * (a + 1) / 2 % m;
			long xb = (long) b * (b + 1) / 2 % m;
			long xc = (long) c * (c + 1) / 2 % m;
			long ans = ((xa * xb) % m * xc) % m;
			System.out.println(ans);
		}
	}

}
