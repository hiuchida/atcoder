import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String a = sc.next();
		String b = sc.next();
		String c = sc.next();
		char al = a.charAt(a.length()-1);
		char bs = b.charAt(0);
		char bl = b.charAt(b.length()-1);
		char cs = c.charAt(0);
		if (al != bs) System.out.println("NO");
		else if (bl != cs) System.out.println("NO");
		else System.out.println("YES");
	}
}
/*
rng gorilla apple

yakiniku unagi sushi

a a a

aaaaaaaaab aaaaaaaaaa aaaaaaaaab
*/
