import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		StringBuilder sb=new StringBuilder(s);
		if (sb.charAt(sb.length()-1)=='a') sb.deleteCharAt(sb.length()-1);
//		System.out.println(sb);
		int n=sb.length();
		for (int i=1; i<=n; i++) {
			int j=n+1-i;
			if (i>=j) break;
			if (sb.charAt(i-1)!=sb.charAt(j-1)) {
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
