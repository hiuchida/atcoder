import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String o = sc.next();
		String e = sc.next();
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<o.length(); i++) {
			sb.append(o.charAt(i));
			if (i<e.length()) sb.append(e.charAt(i));
		}
		System.out.println(sb);
	}
}
/*
xyz
abc

atcoderbeginnercontest
atcoderregularcontest
*/
