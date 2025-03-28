import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		int x = sc.nextInt();
		if (x<=a) System.out.println(1);
		else if (b<x) System.out.println(0);
		else {
			int y=b-a;
			double ans=(double)c/y;
			System.out.println(ans);
		}
	}
}
/*
30 500 20 103

50 500 100 1

1 2 1 1000
*/
