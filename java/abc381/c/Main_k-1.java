import java.util.*;
public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String s = sc.next();
		s += " ";
		int ans = 0;
		if (s.indexOf('/') >= 0) ans++;
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (ch == '1') {
				int c = 0;
				for (int j = i; j < s.length(); j++) {
					if (s.charAt(j) == '1') {
						c++;
					} else if (s.charAt(j) == '/') {
						int d = 0;
						for (int k = j+1; k < s.length(); k++) {
							if (s.charAt(k) == '2') {
								d++;
							} else {
								int e = Math.min(c, d);
								e = e * 2 + 1;
								ans = Math.max(ans, e);
								i = k-1;
								break;
							}
						}
						break;
					} else {
						i = j-1;
						break;
					}
				}
			}
		}
		System.out.println(ans);
	}
}
