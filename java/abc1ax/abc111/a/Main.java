import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		String ans="";
		for (int i=0; i<s.length(); i++) {
			if (s.charAt(i)=='1') ans+="9";
			else ans+="1";
		}
		System.out.println(ans);
	}
}
/*
119

999
*/
