package arc106.a;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			long n = sc.nextLong();
			int m = (int) (n % 10);
			long s;
			int x;
			switch (m) {
			case 8:
				s = 3;
				x = 1;
				break;
			case 4:
				s = 9;
				x = 2;
				break;
			case 2:
				s = 27;
				x = 3;
				break;
			case 6:
				s = 81;
				x = 4;
				break;
			default:
				System.out.println("-1");
				return;
			}
			for (; s < n; s *= 81, x += 4) {
				long dif = n - s;
				int y = 1;
				for (long ss = 5; ss <= dif; ss *= 5, y++) {
					if (ss == dif) {
						System.out.println(x + " " + y);
						return;
					}
				}
			}
			System.out.println("-1");
		}
	}

}
