import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		char[] ary = s.toCharArray();
		Arrays.sort(ary);
		String ss = new String(ary);
		if ("ABC".equals(ss)) System.out.println("Yes");
		else System.out.println("No");
	}
}
/*
BAC

AAC

ABC

ARC
*/
