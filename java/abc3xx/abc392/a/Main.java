import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		if (a*b==c) System.out.println("Yes");
		else if (a*c==b) System.out.println("Yes");
		else if (b*c==a) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
3 15 5

5 3 2

3 3 9
*/
