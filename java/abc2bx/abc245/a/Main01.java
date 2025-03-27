import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		int d = sc.nextInt();
		if (a<b) System.out.println("Takahashi");
		else if (a>b) System.out.println("Aoki");
		else if (c<=d) System.out.println("Takahashi");
		else System.out.println("Aoki");
	}
}
/*
7 0 6 30

7 30 7 30

0 0 23 59
*/
