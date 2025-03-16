import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		char[] ary = new char[s.length()];
		int sp=0;
		for (int i=0; i<s.length(); i++) {
			char ch=s.charAt(i);
			switch (ch) {
			case '(':
			case '[':
			case '<':
				ary[sp++]=ch;
				break;
			case ')':
				if (sp<=0) ng();
				if (ary[--sp]!='(') ng();
				break;
			case ']':
				if (sp<=0) ng();
				if (ary[--sp]!='[') ng();
				break;
			case '>':
				if (sp<=0) ng();
				if (ary[--sp]!='<') ng();
				break;
			}
		}
		System.out.println("Yes");
	}
	static void ng() {
		System.out.println("No");
		System.exit(0);
	}
}
/*
([])<>()

([<)]>

())
*/
