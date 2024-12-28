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
				for (int j=i; true; j++) {
					if (j<s.length() && s.charAt(j) == '0') {
						z++;
					} else {
						i = j-1;
						break;
					}
				}
				ans += z/2;
				ans += z%2;
			} else {
				ans++;
			}
		}
		System.out.println(ans);
	}
}
