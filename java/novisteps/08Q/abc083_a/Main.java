import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		int d = sc.nextInt();
		int ab = a+b;
		int cd = c+d;
		if (ab>cd) System.out.println("Left");
		else if (ab<cd) System.out.println("Right");
		else System.out.println("Balanced");
	}
}
/*
3 8 7 1

3 4 5 2

1 7 6 4
*/
