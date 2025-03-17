import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		s=s.toUpperCase();
		String t = sc.next();
		int idx=0;
		for (int i=0; i<s.length(); i++) {
			if (s.charAt(i)==t.charAt(idx)) {
				idx++;
			}
			if (idx==3) {
				System.out.println("Yes");
				System.exit(0);
			}
		}
		if (idx==2) {
			if (t.charAt(2)=='X') {
				System.out.println("Yes");
				System.exit(0);
			}
		}
		System.out.println("No");
	}
}
/*
narita
NRT

losangeles
LAX

snuke
RNG
*/
