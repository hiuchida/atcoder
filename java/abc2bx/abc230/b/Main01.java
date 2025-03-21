import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		s=s.replaceAll("oxx", "");
		if (s.length()<2) {
			System.out.println("Yes");
			System.exit(0);
		}
		if ("ox".equals(s) || "xx".equals(s) || "xo".equals(s)) {
			System.out.println("Yes");
			System.exit(0);
		}
		System.out.println("No");
	}
}
/*
xoxxoxxo

xxoxxoxo

ox
*/
