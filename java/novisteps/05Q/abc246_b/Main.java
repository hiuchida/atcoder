import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		double r=Math.sqrt(a*a+b*b);
		System.out.println(a/r+" "+b/r);
	}
}
/*
3 4

1 0

246 402
*/
