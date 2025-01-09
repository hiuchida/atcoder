import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int len = 0;
		String[] sa = new String[n];
		for (int i=0; i<n; i++) {
			String s = sc.next();
			sa[i] = s;
			len = Math.max(len, s.length());
		}
		char[][] ary = new char[len][n];
		for (int y=0; y<len; y++) {
			for (int x=0; x<n; x++) {
				String s = sa[n-x-1];
				if (y < s.length()) ary[y][x] = s.charAt(y);
				else ary[y][x] = '*';
			}
		}
		for (int y=0; y<len; y++) {
			for (int x=n-1; x>=0; x--) {
				if (ary[y][x] == '*') ary[y][x] = 0;
				else break;;
			}
		}
		for (int y=0; y<len; y++) {
			for (int x=0; x<n; x++) {
				if (ary[y][x] > 0) System.out.print(ary[y][x]);
			}
			System.out.println();
		}
	}
}
/*
3
abc
de
fghi

3
atcoder
beginner
contest
*/
