import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		String s = sc.next();
		for (int i=0; i<a; i++) {
			if (!isDigit(s.charAt(i))) ng();
		}
		if (s.charAt(a)!='-') ng();
		for (int i=a+1; i<a+1+b; i++) {
			if (!isDigit(s.charAt(i))) ng();
		}
		System.out.println("Yes");
	}
	static boolean isDigit(char ch) {
		return '0'<=ch && ch<='9';
	}
	static void ng() {
		System.out.println("No");
		System.exit(0);
	}
}
/*
3 4
269-6650

1 1
---

1 2
7444
*/
