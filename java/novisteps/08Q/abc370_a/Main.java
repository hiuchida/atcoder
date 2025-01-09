import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int l = sc.nextInt();
		int r = sc.nextInt();
		if (l == 1) {
			if (r == 0) System.out.println("Yes");
			else System.out.println("Invalid");
		} else {
			if (r == 1) System.out.println("No");
			else System.out.println("Invalid");
		}
	}
}
/*
1 0

1 1
*/
