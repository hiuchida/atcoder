import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		String tbl = "aiueo";
		if (tbl.indexOf(s.charAt(0)) >= 0) System.out.println("vowel");
		else System.out.println("consonant");
	}
}
/*
a

z

s
*/
