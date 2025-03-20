import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		boolean bp=(a+b)==c;
		boolean bm=(a-b)==c;
		if (bp) {
			if (bm) System.out.println("?");
			else System.out.println("+");
		} else {
			if (bm) System.out.println("-");
			else System.out.println("!");
		}
	}
}
/*
1 0 1

1 1 2

1 1 0

1 1 1
*/
