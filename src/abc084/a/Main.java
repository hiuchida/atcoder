package abc084.a;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			int m = sc.nextInt();
			int ans = 48 - m;
			System.out.println(ans);
		}
	}

}
