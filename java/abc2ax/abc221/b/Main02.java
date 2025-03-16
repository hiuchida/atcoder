import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		String t = sc.next();
		int neq=-1;
		for (int i=0; i<s.length(); i++) {
			if (s.charAt(i)!=t.charAt(i)) {
				if (neq<0) neq=i;
				else if (neq==i-1) {
					if (s.charAt(neq)!=t.charAt(i) || s.charAt(i)!=t.charAt(neq)) neq=9999;
					else {
						System.out.println("No");
						System.exit(0);
					}
				}
				else {
					System.out.println("No");
					System.exit(0);
				}
			}
		}
		System.out.println("Yes");
	}
}
/*
abc
acb

aabb
bbaa

abcde
abcde
*/
