import java.util.*;
public class Main {
	static void ng(int i) {
		System.out.println(i);
		System.exit(0);
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		String t = sc.next();
		int len=Math.min(s.length(), t.length());
		for (int i=0; i<len; i++) {
			if (s.charAt(i) != t.charAt(i)) ng(i+1);
		}
		if (s.length() == t.length())
			System.out.println(0);
		else
			System.out.println(len+1);
	}
}
