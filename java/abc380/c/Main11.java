import java.util.*;
public class Main {
	public static void main(String[] args){
		long st=System.currentTimeMillis();
		StringBuilder sb = new StringBuilder();
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		String s = sc.next();
		List<Integer> l1 = new ArrayList<>(500000);
		List<Integer> l2 = new ArrayList<>(500000);
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
		for (int i = 0; i < l2.get(k - 2); i++) {
			sb.append(s.charAt(i));
		}
//		System.out.print(s.substring(0, l2.get(k - 2)));
		for (int i = 0; i < l2.get(k - 1) - l1.get(k - 1); i++) {
			sb.append('1');
		}
//		System.out.print(s.substring(l1.get(k - 1), l2.get(k - 1)));
		for (int i = 0; i < l1.get(k - 1) - l2.get(k - 2); i++) {
			sb.append('0');
		}
//		System.out.print(s.substring(l2.get(k - 2), l1.get(k - 1)));
		for (int i = l2.get(k - 1); i < s.length(); i++) {
			sb.append(s.charAt(i));
		}
//		System.out.print(s.substring(l2.get(k - 1)));
		System.out.println(sb.toString());
		long ed=System.currentTimeMillis();
		System.out.println(ed-st);
	}
}
//4865ms
