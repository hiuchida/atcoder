import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		String t = sc.next();
		List<String> ans=new ArrayList<>();
		while (!s.equals(t)) {
			TreeSet<String> set=new TreeSet<>();
			for (int i=0; i<s.length(); i++) {
				if (s.charAt(i)!=t.charAt(i)) {
					StringBuilder sb=new StringBuilder(s);
					sb.setCharAt(i, t.charAt(i));
					set.add(sb.toString());
				}
			}
			String ss=set.first();
			ans.add(ss);
			s=ss;
		}
		System.out.println(ans.size());
		for (String ss : ans) {
			System.out.println(ss);
		}
	}
}
/*
adbe
bcbc

abcde
abcde

afwgebrw
oarbrenq
*/
