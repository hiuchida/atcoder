import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		long ans = 0;
		for (int i=0; i<s.length(); i++) {
			char ch = s.charAt(i);
			if (ch == '0') {
				int z = 0;
				int j=i;
				for (; j<s.length(); j++) {
					if (s.charAt(j) == '0') {
						z++;
					} else {
						break;
					}
				}
				i = j-1;
				ans += z/2;
				ans += z%2;
			} else {
				ans++;
			}
		}
		System.out.println(ans);
	}
}
