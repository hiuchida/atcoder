import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		while (s.charAt(s.length()-1) == '0') s=s.substring(0,s.length()-1);
		if (s.charAt(s.length()-1) == '.') s=s.substring(0,s.length()-1);
		System.out.println(s);
	}
}
/*
1.012

12.340

99.900

0.000
*/
