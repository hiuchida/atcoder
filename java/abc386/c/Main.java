import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int k = sc.nextInt();
		String s = sc.next();
		String t = sc.next();
		int ls = s.length();
		int lt = t.length();
//		if (s.equals(t)) ok();
		if (Math.abs(ls-lt) > 1) ng();
		if (ls==lt) {
			int d=0;
			for (int i=0; i<ls; i++) {
				if (s.charAt(i) != t.charAt(i)) d++;
			}
			if (d>1) ng();
			ok();
		}
		if (ls < lt) comp(s, t);
		else comp(t, s);
	}
	static void comp(String s, String t) {
		int ls = s.length();
		int j=0;
		for (int i=0; i<ls; i++) {
			if (j-i>1) ng();
			if (s.charAt(i) != t.charAt(j)) {
				i--;
			}
			j++;
		}
		ok();
	}
	static void ok() {
		System.out.println("Yes");
		System.exit(0);
	}
	static void ng() {
		System.out.println("No");
		System.exit(0);
	}
}
