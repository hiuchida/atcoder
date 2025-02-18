import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		int ans = 0;
		for (int i=0; i<s.length(); i++) {
			for (int j=1; j<s.length(); j++) {
				if (i+j*2>=s.length()) break;
				if (s.charAt(i)=='A' && s.charAt(i+j)=='B' && s.charAt(i+j*2)=='C') ans++;
			}
		}
		System.out.println(ans);
	}
}
/*
AABCC

ARC

AABAAABBAEDCCCD
*/
