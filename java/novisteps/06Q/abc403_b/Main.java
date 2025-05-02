import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String t=sc.next();
		int n=t.length();
		String u=sc.next();
		int len=u.length();
//		System.out.println(u);
		for (int i=0; i<=n-len; i++) {
			String s=t.substring(i, i+len);
			if (comp(s, u)) {
				System.out.println("Yes");
				System.exit(0);
			}
		}
		System.out.println("No");
	}
	static boolean comp(String s, String u) {
//		System.out.println(s);
		for (int i=0; i<s.length(); i++) {
			if (s.charAt(i)!='?' && s.charAt(i)!=u.charAt(i)) return false;
		}
		return true;
	}
}
/*
tak??a?h?
nashi

??e??e
snuke

????
aoki
*/
