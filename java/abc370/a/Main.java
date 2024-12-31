import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int l = sc.nextInt();
		int r = sc.nextInt();
		if (l == 1) {
			if (r == 0) ok();
			err();
		}
		if (r == 1) ng();
		err();
	}
	static void ok() {
		System.out.println("Yes");
		System.exit(0);
	}
	static void ng() {
		System.out.println("No");
		System.exit(0);
	}
	static void err() {
		System.out.println("Invalid");
		System.exit(0);
	}
}
