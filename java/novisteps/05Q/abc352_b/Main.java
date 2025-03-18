import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		String t = sc.next();
		int idx=0;
		for (int i=0; i<t.length(); i++) {
			if (idx==s.length()) break;
			if (s.charAt(idx)==t.charAt(i)) {
				idx++;
				System.out.print((i+1)+" ");
			}
		}
	}
}
/*
abc
axbxyc

aaaa
bbbbaaaa

atcoder
atcoder
*/
