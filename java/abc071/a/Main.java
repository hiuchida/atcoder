import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int a = sc.nextInt();
		int b = sc.nextInt();
		int xa=Math.abs(x-a);
		int xb=Math.abs(x-b);
		if (xa<xb) System.out.println("A");
		else System.out.println("B");
	}
}
/*
5 2 7

1 999 1000
*/
