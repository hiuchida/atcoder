import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int l = sc.nextInt();
		int r = sc.nextInt();
		String s = sc.next();
		StringBuilder sb=new StringBuilder();
		for (int i=1; i<l; i++) {
			sb.append(s.charAt(i-1));
		}
		for (int i=r; i>=l; i--) {
			sb.append(s.charAt(i-1));
		}
		for (int i=r+1; i<=s.length(); i++) {
			sb.append(s.charAt(i-1));
		}
		System.out.println(sb);
	}
}
/*
3 7
abcdefgh

1 7
reviver

4 13
merrychristmas
*/
