import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		int d = sc.nextInt();
		int ab=Math.abs(a-b);
		int ac=Math.abs(a-c);
		int bc=Math.abs(b-c);
		if (ac<=d) System.out.println("Yes");
		else if (ab<=d && bc<=d) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
4 7 9 3

100 10 1 2

10 10 10 1

1 100 2 10
*/
