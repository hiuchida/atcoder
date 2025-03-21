import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		if (s.length()==1) ok();
		if (s.length()==2) {
			if ("oo".equals(s)) ng();
			else ok();
		}
		char pre1=s.charAt(0);
		char pre2=s.charAt(1);
		for (int i=2; i<s.length(); i++) {
			char ch=s.charAt(i);
			if (pre1=='o') {
				if (pre2=='o') ng();
				if (ch=='o') ng();
			} else if (pre1=='x') {
				if (pre2=='x' && ch=='x') ng();
				if (pre2=='o' && ch=='o') ng();
			}
			pre1=pre2;
			pre2=ch;
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
/*
xoxxoxxo

xxoxxoxo

ox
*/
