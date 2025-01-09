import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String a = sc.next();
		String b = sc.next();
		String s = sc.next();
		StringBuilder sb = new StringBuilder();
		for (char ch : s.toCharArray()) {
			if (ch == a.charAt(0)) sb.append(ch);
			else sb.append(b);
		}
		System.out.println(sb.toString());
	}
}
/*
3 b g
abc

1 s h
s

7 d a
atcoder

10 b a
acaabcabba
*/
