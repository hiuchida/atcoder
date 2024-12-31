import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		String t = sc.next();
		List<String> list = new ArrayList<>();
		while (!s.equals(t)) {
			for (int i=0; i<s.length(); i++) {
				char cs = s.charAt(i);
				char ct = t.charAt(i);
				if (cs > ct) {
					char[] ary = s.toCharArray();
					ary[i] = ct;
					s = new String(ary);
					list.add(s);
				}
			}
			for (int i=s.length()-1; i>=0; i--) {
				char cs = s.charAt(i);
				char ct = t.charAt(i);
				if (cs < ct) {
					char[] ary = s.toCharArray();
					ary[i] = ct;
					s = new String(ary);
					list.add(s);
				}
			}
		}
		System.out.println(list.size());
		for (String ss : list) {
			System.out.println(ss);
		}
	}
}
