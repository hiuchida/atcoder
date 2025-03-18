import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int x = sc.nextInt();
		int y = sc.nextInt();
		int z = sc.nextInt();
		if (x<y) {
			if (x<=z && z<=y) {
				System.out.println("Yes");
				System.exit(0);
			}
		} else {
			if (y<=z && z<=x) {
				System.out.println("Yes");
				System.exit(0);
			}
		}
		System.out.println("No");
	}
}
/*
7 6 1 3

10 3 2 9

100 23 67 45
*/
