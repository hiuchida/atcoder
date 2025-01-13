import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<s.length(); i++) {
			char ch=s.charAt(i);
			switch (ch) {
			case '0':
			case '1':
				sb.append(ch);
				break;
			case 'B':
				if (sb.length()>0) {
					sb.deleteCharAt(sb.length()-1);
				}
				break;
			}
		}
		System.out.println(sb);
	}
}
/*
01B0

0BB1
*/
