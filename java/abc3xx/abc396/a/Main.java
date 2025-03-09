import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int pre1=sc.nextInt();
		int pre2=sc.nextInt();
		for (int i=2; i<n; i++) {
			int a = sc.nextInt();
			if (pre1 == pre2 && pre2==a) {
				System.out.println("Yes");
				System.exit(0);
			}
			pre1=pre2;
			pre2=a;
		}
		System.out.println("No");
	}
}
/*
5
1 4 4 4 2

6
2 4 4 2 2 4

8
1 4 2 5 7 7 7 2

10
1 2 3 4 5 6 7 8 9 10

13
1 1 1 1 1 1 1 1 1 1 1 1 1
*/
