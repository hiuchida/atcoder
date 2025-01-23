import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		int n = s.length();
		TreeSet<String> set = new TreeSet<>();
		set.add(s);
		StringBuilder sb = new StringBuilder(s);
		for (int i=0; i<n; i++) {
			char ch=sb.charAt(0);
			sb.delete(0, 1);
			sb.append(ch);
			set.add(sb.toString());
		}
		System.out.println(set.first());
		System.out.println(set.last());
	}
}
/*
aaba

z

abracadabra
*/
