import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		char[] ary=s.toCharArray();
		String t = sc.next();
		if (s.equals(t)) ok();
		for (int k=1; k<26; k++) {
			char[] as=new char[s.length()];
			for (int i=0; i<s.length(); i++) {
				if (ary[i]+k>'z') as[i]=(char)(ary[i]+k-26);
				else as[i]=(char)(ary[i]+k);
			}
			String ss=new String(as);
			if (ss.equals(t)) ok();
		}
		System.out.println("No");
	}
	static void ok() {
		System.out.println("Yes");
		System.exit(0);
	}
}
/*
abc
ijk

z
a

ppq
qqp

atcoder
atcoder
*/
