import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		int d = sc.nextInt();
		double ra=(double)b/a;
		double rb=(double)d/c;
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
