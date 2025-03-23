import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		int d = sc.nextInt();
		int ra=10000*b/a;
		int rb=10000*d/c;
		if (ra>rb) System.out.println("TAKAHASHI");
		else if (ra<rb) System.out.println("AOKI");
		else System.out.println("DRAW ");
	}
}
/*
5 2 6 3

100 80 100 73

66 30 55 25
*/
