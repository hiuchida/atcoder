import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		StringBuilder sb=new StringBuilder();
		for (int i=0; i<6/s.length(); i++) {
			sb.append(s);
		}
		System.out.println(sb);
	}
}
/*
abc

zz
*/
