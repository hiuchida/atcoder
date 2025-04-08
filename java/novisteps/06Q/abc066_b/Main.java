import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		for (int i=s.length()-1; i>0; i--) {
			String ss=s.substring(0, i);
			if (check(ss)) {
				System.out.println(ss.length());
				System.exit(0);
			}
		}
	}
	static boolean check(String s) {
		int len=s.length();
		if (len%2==1) return false;
		for (int i=0; i<len/2; i++) {
			if (s.charAt(i)!=s.charAt(i+len/2)) return false;
		}
		return true;
	}
}
/*
abaababaab

xxxx

abcabcabcabc

akasakaakasakasakaakas
*/
