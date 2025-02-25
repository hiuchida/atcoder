import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		for (int i=0; i<s.length(); i++) {
			char ch=s.charAt(i);
			if (ch=='2') System.out.print(ch);
		}
		System.out.println();
	}
}
/*
20250222

2

22222000111222222
*/
