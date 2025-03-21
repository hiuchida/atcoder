import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		String t = sc.next();
		String tbl="atcoder";
		for (int i=0; i<s.length(); i++) {
			char cs=s.charAt(i);
			char ct=t.charAt(i);
			if (cs!=ct) {
				if (cs=='@') {
					if (tbl.indexOf(ct)<0) ng();
				} else if (ct=='@') {
					if (tbl.indexOf(cs)<0) ng();
				} else {
					ng();
				}
			}
		}
		System.out.println("You can win");
	}
	static void ng() {
		System.out.println("You will lose");
		System.exit(0);
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
