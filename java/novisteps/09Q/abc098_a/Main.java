import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int ans = Math.max(a+b, Math.max(a-b, a*b));
		System.out.println(ans);
	}
}
/*
3 1

4 -2

0 0
*/