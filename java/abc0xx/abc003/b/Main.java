import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		String t = sc.next();
		String tbl="atcoder";
		for (int i=0; i<s.length(); i++) {
			if (s.charAt(i)!=t.charAt(i)) {
				if (s.charAt(i)=='@') {
					if (t.charAt(i)=='@') {
					} else if (tbl.indexOf(t.charAt(i))<0) {
						System.out.println("You will lose");
						System.exit(0);
					}
				} else if (t.charAt(i)=='@') {
					if (tbl.indexOf(s.charAt(i))<0) {
						System.out.println("You will lose");
						System.exit(0);
					}
				} else {
					System.out.println("You will lose");
					System.exit(0);
				}
			}
		}
		System.out.println("You can win");
	}
}
/*
ch@ku@ai
choku@@i

aoki
@ok@

arc
abc
*/
