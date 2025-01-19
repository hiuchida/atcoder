import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		for (int i=c; i<=b; i+=c) {
			if (a<=i) {
				System.out.println(i);
				System.exit(0);
			}
		}
		System.out.println(-1);
	}
}
/*
123 456 100

630 940 314
*/
