import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		String t = sc.next();
		List<String> list = new ArrayList<>();
		while (!s.equals(t)) {
			char[] arym = new char[s.length()];
			Arrays.fill(arym, 'z');
			String min = new String(arym);
			for (int i=0; i<s.length(); i++) {
				char cs = s.charAt(i);
				char ct = t.charAt(i);
				if (cs != ct) {
					char[] ary = s.toCharArray();
					ary[i] = ct;
					String ss = new String(ary);
					if (ss.compareTo(min) < 0) {
						min = ss;
					}
				}
			}
			list.add(min);
			s = min;
		}
		System.out.println(list.size());
		for (String ss : list) {
			System.out.println(ss);
		}
	}
}
