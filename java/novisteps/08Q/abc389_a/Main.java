import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		int a=s.charAt(0)-'0';
		int b=s.charAt(2)-'0';
		int ans = a*b;
		System.out.println(ans);
	}
}
/*
3x8

9x9
*/
