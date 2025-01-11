import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int x = sc.nextInt();
		int ab = a+b;
		if (ab<x || x<a) System.out.println("NO");
		else System.out.println("YES");
	}
}
/*
3 5 4

2 2 6

5 3 2
*/
