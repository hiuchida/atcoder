import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		char[] ary=s.toCharArray();
		int l=0;
		int r=s.length()-1;
		while (l<r && ary[l]=='a' && ary[r]=='a') {
			l++;
			r--;
		}
		while (l<r && ary[r]=='a') r--;
		String ss=s.substring(l, r+1);
//		StringBuilder sb=new StringBuilder(s);
//		while (sb.length()>0 && sb.charAt(sb.length()-1)=='a') sb.deleteCharAt(sb.length()-1);
//		while (sb.length()>0 && sb.charAt(0)=='a') sb.deleteCharAt(0);
//		System.out.println(ss);
		int n=ss.length();
		for (int i=1; i<=n; i++) {
			int j=n+1-i;
			if (i>=j) break;
			if (ss.charAt(i-1)!=ss.charAt(j-1)) {
				System.out.println("No");
				System.exit(0);
			}
		}
		System.out.println("Yes");
	}
}
/*
kasaka

atcoder

php
*/
