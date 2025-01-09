import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		if (b>c) c+=24;
		int aa=a+24;
		if (b<=a && a<=c) ng();
		if (b<=aa && aa<=c) ng();
		System.out.println("Yes");
	}
	static void ng() {
		System.out.println("No");
		System.exit(0);
	}
}
/*
21 8 14

0 21 7

10 7 17
*/
