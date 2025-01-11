import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		int r=0, m=0;
		for (int i=0; i<s.length(); i++) {
			if (s.charAt(i)=='R') r=i;
			else if (s.charAt(i)=='M') m=i;
		}
		if (r<m) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
RSM

SMR
*/
