import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s1 = sc.next();
		String s2 = sc.next();
		char c11=s1.charAt(0);
		char c12=s1.charAt(1);
		char c13=s1.charAt(2);
		char c21=s2.charAt(0);
		char c22=s2.charAt(1);
		char c23=s2.charAt(2);
		if (c11!=c23) ng();
		if (c12!=c22) ng();
		if (c13!=c21) ng();
		System.out.println("YES");
	}
	static void ng() {
		System.out.println("No");
		System.exit(0);
	}
}
/*
pot
top

tab
bet

eye
eel
*/
