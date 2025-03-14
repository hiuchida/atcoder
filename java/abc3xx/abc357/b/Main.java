import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		int cntl=0;
		int cntu=0;
		for (int i=0; i<s.length(); i++) {
			char ch=s.charAt(i);
			if ('A'<=ch&&ch<='Z') cntu++;
			else cntl++;
		}
		if (cntu>cntl) {
			s=s.toUpperCase();
		} else {
			s=s.toLowerCase();
		}
		System.out.println(s);
	}
}
/*
AtCoder

SunTORY

a
*/
