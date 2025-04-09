import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		int c=0;
		for (int i=0; i<s.length(); i++) {
			c+=s.charAt(i)-'0';
		}
		if (c%9==0) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
123456789

0

31415926535897932384626433832795028841971693993751058209749445923078164062862089986280
*/
