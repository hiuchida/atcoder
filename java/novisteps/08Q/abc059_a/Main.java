import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s1 = sc.next();
		String s2 = sc.next();
		String s3 = sc.next();
		StringBuilder sb = new StringBuilder();
		sb.append(s1.charAt(0));
		sb.append(s2.charAt(0));
		sb.append(s3.charAt(0));
		String s = sb.toString().toUpperCase();
		System.out.println(s);
	}
}
/*
atcoder beginner contest

resident register number

k nearest neighbor

async layered coding
*/
