import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		if (s.startsWith("ABC")) {
			int v=Integer.parseInt(s.substring(3));
			if (1<=v && v<=349 && v!=316) {
				System.out.println("Yes");
				System.exit(0);
			}
		}
		System.out.println("No");
	}
}
/*
ABC349

ABC350

ABC316
*/
