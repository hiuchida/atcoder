import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		long ans = 0;
		int z = 0;
		for (int i=0; i<s.length(); i++) {
			char ch = s.charAt(i);
			if (ch == '0') {
				z++;
			} else {
				ans += z/2;
				ans += z%2;
				z = 0;
				ans++;
			}
		}
		if (z > 0) {
			ans += z/2;
			ans += z%2;
		}
		System.out.println(ans);
	}
}
