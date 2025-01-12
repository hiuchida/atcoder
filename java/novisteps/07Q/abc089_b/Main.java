import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for (int i=0; i<n; i++) {
			String s = sc.next();
			if ('Y'==s.charAt(0)) {
				System.out.println("Four");
				System.exit(0);
			}
		}
		System.out.println("Three");
	}
}
/*
6
G W Y P Y W

9
G W W G P W P G G

8
P Y W G Y W Y Y
*/
