import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		char[] ary=s.toCharArray();
		StringBuilder sb=new StringBuilder();
		for (int i=0; i<ary.length; i++) {
			if (checkUpper(ary[i])) sb.append(ary[i]);
		}
		System.out.println(sb);
	}
	static boolean checkUpper(char ch) {
		if ('A'<=ch && ch<='Z') return true;
		return false;
	}
}
/*
AtCoderBeginnerContest

PaymentRequired

program
*/
