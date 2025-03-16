import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		String t = sc.next();
		boolean brepl=false;
		for (int i=0; i<s.length(); i++) {
			if (s.charAt(i)!=t.charAt(i)) {
				if (brepl) {
					System.out.println("No");
					System.exit(0);
				}
				if (i+1<s.length()) {
					if (s.charAt(i)==t.charAt(i+1) && s.charAt(i+1)==t.charAt(i)) {
						brepl=true;
						i++;
					} else {
						System.out.println("No");
						System.exit(0);
					}
				} else {
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
