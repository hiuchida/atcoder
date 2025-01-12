import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<s.length(); i+=2) {
			sb.append(s.charAt(i));
		}
		System.out.println(sb);
	}
}
/*
atcoder

aaaa

z

fukuokayamaguchi
*/
