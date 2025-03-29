import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		if (a>c) {
			int t=a;
			a=c;
			c=t;
		}
		if (a<=b && b<=c) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
5 3 2

2 5 3

100 100 100
*/
