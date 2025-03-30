import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		for (int i=1; i<=b; i++) {
			if (a*i%b==c) {
				System.out.println("YES");
				System.exit(0);
			}
		}
		System.out.println("NO");
	}
}
/*
7 5 1

2 2 1

1 100 97

40 98 58

77 42 36
*/
