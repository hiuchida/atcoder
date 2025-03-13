import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int pre = sc.nextInt();
		for (int i=1; i<n; i++) {
			int a = sc.nextInt();
			if (pre>=a) {
				System.out.println("No");
				System.exit(0);
			}
			pre=a;
		}
		System.out.println("Yes");
	}
}
/*
3
1 2 5

3
3 9 5

10
1 1 2 3 5 8 13 21 34 55
*/
