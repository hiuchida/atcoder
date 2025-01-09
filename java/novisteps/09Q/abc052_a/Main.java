import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		int d = sc.nextInt();
		int ab = a*b;
		int cd = c*d;
		int ans = Math.max(ab, cd);
		System.out.println(ans);
	}
}
/*
3 5 2 7

100 600 200 300
*/
