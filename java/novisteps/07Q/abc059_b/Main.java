import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String a = sc.next();
		String b = sc.next();
		if (a.length()<b.length()) System.out.println("LESS");
		else if (a.length()>b.length()) System.out.println("GREATER");
		else {
			int cmp = a.compareTo(b);
			if (cmp<0) System.out.println("LESS");
			else if (cmp>0) System.out.println("GREATER");
			else System.out.println("EQUAL");
		}
	}
}
/*
36
24

850
3777

9720246
22516266

123456789012345678901234567890
234567890123456789012345678901
*/
