import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		int n = sc.nextInt();
		n--;
		int a=n/5;
		int b=n%5;
		StringBuilder sb=new StringBuilder();
		sb.append(s.charAt(a)).append(s.charAt(b));
		System.out.println(sb);
	}
}
/*
abcde
8

aeiou
22

vwxyz
25
*/
