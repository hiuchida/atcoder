import java.util.*;
public class Main {
	public static void main(String[] args){
		long st=System.currentTimeMillis();
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		String s = sc.next();
		List<Integer> l1 = new ArrayList<>();
		List<Integer> l2 = new ArrayList<>();
		if (s.charAt(0) == '1') {
			l1.add(0);
		}
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '0') {
				for (int j = i; j < s.length(); j++) {
					if (s.charAt(j) == '1') {
						l1.add(j);
						i = j - 1;
						break;
					}
				}
			} else {
				for (int j = i; j < s.length(); j++) {
					if (s.charAt(j) == '0') {
						l2.add(j);
						i = j - 1;
						break;
					}
				}
			}
		}
		if (s.charAt(s.length() - 1) == '1') {
			l2.add(s.length());
		}
//		System.out.println(l1);
//		System.out.println(l2);
//		if (s.charAt(0) == '0') {
			String ss = s.substring(0, l2.get(k - 2));
			ss += s.substring(l1.get(k - 1), l2.get(k - 1));
			ss += s.substring(l2.get(k - 2), l1.get(k - 1));
			ss += s.substring(l2.get(k - 1));
			System.out.println(ss);
//		}
		long ed=System.currentTimeMillis();
		System.out.println(ed-st);
	}
}
//6171ms
