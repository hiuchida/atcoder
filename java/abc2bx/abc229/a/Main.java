import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s1 = sc.next();
		String s2 = sc.next();
		if ("##".equals(s1) || "##".equals(s2)) {
			System.out.println("Yes");
			System.exit(0);
		}
		if (s1.equals(s2)) {
			System.out.println("Yes");
			System.exit(0);
		}
		System.out.println("No");
	}
}
/*
##
.#

.#
#.
*/
