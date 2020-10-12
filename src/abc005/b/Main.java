package abc005.b;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			int n = sc.nextInt();
			int ans = Integer.MAX_VALUE;
			for (int i = 0; i < n; i++) {
				int t = sc.nextInt();
				ans = Math.min(ans, t);
			}
			System.out.println(ans);
		}
	}

}
